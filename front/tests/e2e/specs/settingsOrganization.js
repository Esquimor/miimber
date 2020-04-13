/**
 * Verify if an organization exist
 */

describe("Settings Organizations", () => {
  it("Verify if an organization exist", () => {
    cy.login("admin");
    cy.saveLocalStorage();
    cy.createOrganization();
    cy.visit("/settings/organization");
    cy.get("#SettingsOrganization-list")
      .find(".SettingsOrganizationItem")
      .click();
    cy.url().should("include", "/organization-manage");
  });
});
