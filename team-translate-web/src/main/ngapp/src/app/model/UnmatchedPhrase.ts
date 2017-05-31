import * as models from './models';

export interface UnmatchedPhrase {

    isSelectedForEmail?: boolean;

    phrase?: String;

    closeMatches?: Array<models.Match>;

}
