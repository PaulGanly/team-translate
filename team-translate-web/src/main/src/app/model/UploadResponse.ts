import * as models from './models';

export interface UploadResponse {
    propertiesFile?: string;

    matchesFound?: number;

    unmatchedPhrases?: Array<models.UnmatchedArray>;

}
