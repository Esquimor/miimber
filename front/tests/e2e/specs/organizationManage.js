import { v4 as uuidv4 } from "uuid";

describe("Organization Manage", () => {
  before(() => {
    cy.login("admin");
    cy.createOrganization();
    cy.saveLocalStorage();
  });

  beforeEach(() => {
    cy.restoreLocalStorage().then(() => {
      cy.visit(
        `/organization-manage/${localStorage.getItem("organizationId")}/members`
      );
      cy.get("button#OrganizationOrganization-edit").click();
      cy.get("input#OrganizationOrganizationEdit-name.input").as("name");
      cy.get("button#TemplateSidePanelRight-cancel").as("cancel");
      cy.get("button#TemplateSidePanelRight-confirm").as("confirm");
    });
  });

  afterEach(() => {
    cy.reload();
  });

  it("Empty name input", () => {
    cy.get("@name")
      .clear()
      .blur();
    cy.get("@name").should("have.class", "is-danger");

    cy.get("@name")
      .type(uuidv4())
      .blur();
    cy.get("@name").not("have.class", "is-danger");
  });

  it("Confirm button", () => {
    cy.get("@name")
      .clear()
      .blur();
    cy.get("@confirm").should("be.disabled");

    cy.get("@name")
      .type(uuidv4())
      .blur();
    cy.get("@confirm").not("be.disabled");
  });

  it("Edit", () => {
    cy.get("@name")
      .clear()
      .type(uuidv4());
    cy.get("@confirm").click();
    cy.get("#TemplateSidePanelRight").should("not.exist");
  });
});
