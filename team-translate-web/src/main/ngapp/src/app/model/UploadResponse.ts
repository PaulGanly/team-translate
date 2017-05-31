import * as models from './models';

export interface UploadResponse {
    fileName?: string;

    totalPhrases?: number;

    numberOfExactMatches?: number;

    unmatchedPhrases?: Array<String>;

}
