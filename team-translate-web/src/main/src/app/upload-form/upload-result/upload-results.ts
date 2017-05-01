export class CloseMatchesResult {
  id: number;
  translatedFrom: string;
  matchPercentage: number;
  translatedTo: string;
}

const CLOSE_MATCHES: CloseMatchesResult[] = [
  { id: 11, translatedFrom: 'Hello', matchPercentage: 50, 	translatedTo: 'Dia dhuit' },
  { id: 12, translatedFrom: 'Hello', matchPercentage: 50, 	translatedTo: 'Dia dhuit'	},
  { id: 13, translatedFrom: 'Hello', matchPercentage: 50, 	translatedTo: 'Dia dhuit'	}
];

export class UploadResult {
  id: number;
  toBeTranslated: string;
  closeMatches: CloseMatchesResult[];
}

export const RESULTS: UploadResult[] = [
  { id: 11, toBeTranslated: 'Hello', closeMatches: CLOSE_MATCHES},
  { id: 12, toBeTranslated: 'Hello', closeMatches: CLOSE_MATCHES},
  { id: 13, toBeTranslated: 'Hello', closeMatches: CLOSE_MATCHES},
  { id: 14, toBeTranslated: 'Hello', closeMatches: CLOSE_MATCHES},
  { id: 15, toBeTranslated: 'Hello', closeMatches: CLOSE_MATCHES},
  { id: 16, toBeTranslated: 'Hello', closeMatches: CLOSE_MATCHES},
  { id: 17, toBeTranslated: 'Hello', closeMatches: CLOSE_MATCHES},
  { id: 18, toBeTranslated: 'Hello', closeMatches: CLOSE_MATCHES},
  { id: 19, toBeTranslated: 'Hello', closeMatches: CLOSE_MATCHES},
  { id: 20, toBeTranslated: 'Hello', closeMatches: CLOSE_MATCHES}
];
