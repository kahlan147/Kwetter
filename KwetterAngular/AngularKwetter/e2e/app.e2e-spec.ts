import { AngularKwetterPage } from './app.po';

describe('angular-kwetter App', function() {
  let page: AngularKwetterPage;

  beforeEach(() => {
    page = new AngularKwetterPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
