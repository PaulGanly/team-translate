import { Inject, Injectable, Optional } from '@angular/core';
import { Http, Headers, URLSearchParams } from '@angular/http';
import { RequestMethod, RequestOptions, RequestOptionsArgs } from '@angular/http';
import { Response, ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import * as models from '../model/models';

@Injectable()
export class UploadService {
  language: string;
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
  public uploadFile(language: string, file: any, extraHttpRequestParams?: any): Observable<models.UploadResponse> {
    return this.uploadFileWithHttpInfo(language, file, extraHttpRequestParams)
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
    for (let key in objB) {
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
    let queryParameters = new URLSearchParams();
    let headers = new Headers(this.defaultHeaders.toJSON());
    let formParams = new URLSearchParams();

    if (language === null || language === undefined) {
      throw new Error('Required parameter language was null or undefined when calling uploadFile.');
    }

    if (file === null || file === undefined) {
      throw new Error('Required parameter file was null or undefined when calling uploadFile.');
    }

    let consumes: string[] = [
      'multipart/form-data'
    ];

    let produces: string[] = [
      'application/json'
    ];

    headers.set('Content-Type', 'multipart/form-data; boundary=---------------------------186035562730765173675680‌​113');

    if (language !== undefined) {
      formParams.set('language', language);
    }

    if (file !== undefined) {
      formParams.set('file', <any>file);
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

    return this.http.request(path, requestOptions);
  }

}
