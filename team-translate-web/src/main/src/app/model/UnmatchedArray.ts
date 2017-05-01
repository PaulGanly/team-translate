import * as models from './models';

export interface UnmatchedArray {
    phrase?: string;

    closeMatches?: Array<models.Match>;

}
