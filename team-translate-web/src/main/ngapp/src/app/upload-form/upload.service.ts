import { Inject, Injectable, Optional } from '@angular/core';
import { Http, Headers, URLSearchParams } from '@angular/http';
import { RequestMethod, RequestOptions, RequestOptionsArgs } from '@angular/http';
import { Response, ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import * as models from '../model/models';

@Injectable()
export class UploadService {
  public uploadResult: models.UploadResponse;
  public language: string;
  public file: any;
  protected basePath = 'http://localhost:8082/';
  public defaultHeaders: Headers = new Headers();
  uploadResponse = new Observable<models.UploadResponse[]>();

  constructor(protected http: Http) { }

  /**
     * Uploads a properties file for translation
     *
     * @param language Language file phrases are written in
     * @param file File to Upload
     */
  public uploadFile(language: string, file: any): Observable<models.UploadResponse> {

    this.language = language;
    this.file = file;

    return this.uploadFileWithHttpInfo(language, file)
      .map((response: Response) => {
        if (response.status === 204) {
          return undefined;
        } else {
          return response.json();
        }
      });
  }

  /**
   * Translates and downloads a properties file
   *
   */
  public downloadTranslatedFile() {

    return this.downloadTranslatedPropertiesFile()
      .map((response: Response) => {
        if (response.status === 204) {
          return undefined;
        } else {
          return response;
        }
      });
  }

  /**
    * Uploads a phrase to find any similar phrases in the database
    *
    * @param phrase the phrase
    */
  public searchForViewSimilar(phrase: string) {

    return this.uploadPhraseToFindSimilar(phrase)
      .map((response: Response) => {
        if (response.status === 204) {
          return undefined;
        } else {
          return response.json();
        }
      });
  }

  /**
   *
   * Extends object by coping non-existing properties.
   * @param objA object to be extended
   * @param objB source object
   */
  public extendObj<T1, T2 extends T1>(objA: T2, objB: T2): T1 | T2 {
    for (const key in objB) {
      if (objB.hasOwnProperty(key)) {
        objA[key] = objB[key];
      }
    }
    return objA;
  }

  /**
    * Uploads a properties file for translation
    *
    * @param language Language file phrases are written in
    * @param file File to Upload
    */
  public uploadFileWithHttpInfo(language: string, file: any, extraHttpRequestParams?: any): Observable<Response> {
    const path = this.basePath + 'translation/uploadFile';
    const queryParameters = new URLSearchParams();
    const headers = new Headers(this.defaultHeaders.toJSON());
    const formData = new FormData();

    if (language === null || language === undefined) {
      throw new Error('Required parameter language was null or undefined when calling uploadFile.');
    }

    if (file === null || file === undefined) {
      throw new Error('Required parameter file was null or undefined when calling uploadFile.');
    }

    const consumes: string[] = [
      'multipart/form-data'
    ];

    const produces: string[] = [
      'application/json'
    ];

    const boundary = '-----------------------------' + Math.floor(Math.random() * Math.pow(10, 8));

    headers.set('Content-Type', 'multipart/form-data; boundary=' + boundary);

    if (language !== undefined) {
      queryParameters.set('language', <any>language);
    }

    if (file !== undefined) {
      formData.append('file', <any>file);
    }

    const requestOptions: RequestOptionsArgs = new RequestOptions({
      method: RequestMethod.Post,
      headers: new Headers(),
      body: formData,
      search: queryParameters
    });

    return this.http.request(path, requestOptions);
  }

  /**
    * Uploads a properties file for translation
    *
    * @param language Language file phrases are written in
    * @param file File to Upload
    */
  public downloadTranslatedPropertiesFile() {
    const path = this.basePath + 'translation/downloadTranslatedFile';
    const queryParameters = new URLSearchParams();
    const headers = new Headers(this.defaultHeaders.toJSON());
    const formData = new FormData();

    if (this.language === null || this.language === undefined) {
      throw new Error('Required parameter language was null or undefined when calling uploadFile.');
    }

    if (this.file === null || this.file === undefined) {
      throw new Error('Required parameter file was null or undefined when calling uploadFile.');
    }

    const consumes: string[] = [
      'multipart/form-data'
    ];

    const produces: string[] = [
      'application/json'
    ];

    const boundary = '-----------------------------' + Math.floor(Math.random() * Math.pow(10, 8));

    headers.set('Content-Type', 'multipart/form-data; boundary=' + boundary);

    if (this.language !== undefined) {
      queryParameters.set('language', <any>this.language);
    }

    if (this.file !== undefined) {
      formData.append('file', <any>this.file);
    }

    const requestOptions: RequestOptionsArgs = new RequestOptions({
      method: RequestMethod.Post,
      headers: new Headers(),
      body: formData,
      search: queryParameters
    });

    return this.http.request(path, requestOptions);
  }


  /**
    * Uploads a phrase to find any similar phrases in the database
    *
    * @param phrase the phrase
    */
  public uploadPhraseToFindSimilar(phrase: string) {
    const path = this.basePath + `translation/find`;
    const queryParameters = new URLSearchParams();
    const headers = new Headers(this.defaultHeaders.toJSON());

    if (phrase === null || phrase === undefined) {
      throw new Error('Required parameter phrase was null or undefined when calling findTranslationsForPhrase.');
    }

    if (this.language === null || this.language === undefined) {
      throw new Error('Required parameter language was null or undefined when calling findTranslationsForPhrase.');
    }
    if (phrase !== undefined) {
      queryParameters.set('phrase', <any>phrase);
    }
    if (this.language !== undefined) {
      queryParameters.set('language', <any>this.language);
    }

    const consumes: string[] = [
    ];

    const produces: string[] = [
      'application/json'
    ];

    const requestOptions: RequestOptionsArgs = new RequestOptions({
      method: RequestMethod.Get,
      headers: headers,
      search: queryParameters
    });

    return this.http.request(path, requestOptions);
  }


}
