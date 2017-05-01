import { Inject, Injectable, Optional } from '@angular/core';
import { Http, Headers, URLSearchParams } from '@angular/http';
import { RequestMethod, RequestOptions, RequestOptionsArgs } from '@angular/http';
import { Response, ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import * as models from '../model/models';


@Injectable()
export class SearchService {
  protected basePath = 'http://localhost:8082/';
  public defaultHeaders: Headers = new Headers();
  foundMatches = new Observable<models.Match[]>();

  constructor(protected http: Http) {}

  /**
   * Search for the translation of the provided phrase.
   * @param language 
   * @param phrase 
   */
  search(language: string, phrase: string) { 
    this.foundMatches = this.findTranslationsForPhrase(phrase, language);
    return this.foundMatches;
  }

  /**
   * 
   * Extends object by coping non-existing properties.
   * @param objA object to be extended
   * @param objB source object
   */
  private extendObj<T1, T2 extends T1>(objA: T2, objB: T2): T1 | T2 {
    for (let key in objB) {
      if (objB.hasOwnProperty(key)) {
        objA[key] = objB[key];
      }
    }
    return objA;
  }

  /**
    * Finds Translations for the given phrase
    * Language of given phrase must be supplied
    * @param phrase Phrase to be translated
    * @param language Language phrase is written in
    */
  public findTranslationsForPhrase(phrase: string, language: string, extraHttpRequestParams?: any): Observable<Array<models.Match>> {
    return this.findTranslationsForPhraseWithHttpInfo(phrase, language, extraHttpRequestParams)
      .map((response: Response) => {
        if (response.status === 204) {
          return undefined;
        } else {
          return response.json();
        }
      });
  }

  /**
 * Finds Translations for the given phrase
 * Language of given phrase must be supplied
 * @param phrase Phrase to be translated
 * @param language Language phrase is written in
 */
  public findTranslationsForPhraseWithHttpInfo(phrase: string, language: string, extraHttpRequestParams?: any): Observable<Response> {
    const path = this.basePath + `translation/find`;

    let queryParameters = new URLSearchParams();
    let headers = new Headers(this.defaultHeaders.toJSON());

    if (phrase === null || phrase === undefined) {
      throw new Error('Required parameter phrase was null or undefined when calling findTranslationsForPhrase.');
    }

    if (language === null || language === undefined) {
      throw new Error('Required parameter language was null or undefined when calling findTranslationsForPhrase.');
    }
    if (phrase !== undefined) {
      queryParameters.set('phrase', <any>phrase);
    }
    if (language !== undefined) {
      queryParameters.set('language', <any>language);
    }

    let consumes: string[] = [
    ];

    let produces: string[] = [
      'application/json'
    ];

    let requestOptions: RequestOptionsArgs = new RequestOptions({
      method: RequestMethod.Get,
      headers: headers,
      search: queryParameters
    });

    if (extraHttpRequestParams) {
      requestOptions = this.extendObj(requestOptions, extraHttpRequestParams);
    }

    return this.http.request(path, requestOptions);
  }


}