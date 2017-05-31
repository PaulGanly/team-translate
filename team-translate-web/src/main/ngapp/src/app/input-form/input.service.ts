import { Inject, Injectable, Optional } from '@angular/core';
import { Http, Headers, URLSearchParams } from '@angular/http';
import { RequestMethod, RequestOptions, RequestOptionsArgs } from '@angular/http';
import { Response, ResponseContentType } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class InputService {
  protected basePath = 'http://localhost:8082/';
  public defaultHeaders: Headers = new Headers();

  constructor(protected http: Http) { }

	/**
     * 
     * Extends object by coping non-existing properties.
     * @param objA object to be extended
     * @param objB source object
     */
  private extendObj<T1, T2 extends T1>(objA: T2, objB: T2): T1 | T2 {
    for (const key in objB) {
      if (objB.hasOwnProperty(key)) {
        objA[key] = objB[key];
      }
    }
    return objA;
  }

  /**
   * Input a new translation with form data
   * 
   * @param english Phrase in english
   * @param irish Phrase in Irish
   * @param context Phrase Context
   */
  public inputTranslationWithForm(english: string, irish: string, context: string, extraHttpRequestParams?: any): Observable<{}> {
    console.log('Input a new translation' + english + ' ' + irish + ' ' + context);
    return this.inputTranslationWithFormWithHttpInfo(english, irish, context, extraHttpRequestParams)
      .map((response: Response) => {
        if (response.status === 204) {
          return undefined;
        } else {
          return response;
        }
      });
  }

  /**
   * Input a new translation with form data
   * 
   * @param english Phrase in english
   * @param irish Phrase in Irish
   * @param context Phrase Context
   */
  public inputTranslationWithFormWithHttpInfo(english: string,
                                              irish: string,
                                              context: string, extraHttpRequestParams?: any): Observable<Response> {
    const path = this.basePath + `translation/input`;

    const queryParameters = new URLSearchParams();
    const headers = new Headers(this.defaultHeaders.toJSON());
    const formParams = new URLSearchParams();

    console.log('Input a new translation with Http' + english + ' ' + irish + ' ' + context + ' ' + path);

    if (english === null || english === undefined) {
      throw new Error('Required parameter english was null or undefined when calling inputTranslationWithForm.');
    }

    if (irish === null || irish === undefined) {
      throw new Error('Required parameter irish was null or undefined when calling inputTranslationWithForm.');
    }

    if (context === null || context === undefined) {
      throw new Error('Required parameter context was null or undefined when calling inputTranslationWithForm.');
    }

    const consumes: string[] = [
      'application/x-www-form-urlencoded'
    ];

    const produces: string[] = [
      'application/json'
    ];

    headers.set('Content-Type', 'application/x-www-form-urlencoded');

    if (english !== undefined) {
      formParams.set('english', <any>english);
    }
    if (irish !== undefined) {
      formParams.set('irish', <any>irish);
    }
    if (context !== undefined) {
      formParams.set('context', <any>context);
    }

    let requestOptions: RequestOptionsArgs = new RequestOptions({
      method: RequestMethod.Post,
      headers: headers,
      body: formParams.toString(),
      search: queryParameters
    });

    if (extraHttpRequestParams) {
      requestOptions = this.extendObj(requestOptions, extraHttpRequestParams);
    }

    console.log('Request URL' + requestOptions.url);

    return this.http.request(path, requestOptions);
  }
}
