import { TeamTranslatePage } from './app.po';

describe('team-translate App', () => {
  let page: TeamTranslatePage;

  beforeEach(() => {
    page = new TeamTranslatePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
