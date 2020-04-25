/**
 * Test security page
 */

// import { v4 as uuidv4 } from "uuid";

describe("Porfile", () => {
  before(() => {
    cy.login("admin");
    cy.saveLocalStorage();
  });

  beforeEach(() => {
    cy.restoreLocalStorage();
    cy.visit("/settings/security");
    cy.get("input#SettingsSecurity-oldPassword.input").as("oldPassword");
    cy.get("input#SettingsSecurity-newPassword.input").as("newPassword");
    cy.get("input#SettingsSecurity-confirmPassword.input").as(
      "confirmPassword"
    );
    cy.get("button#SettingsSecurity-submit").as("submit");
  });

  afterEach(() => {
    cy.reload(true);
  });

  it("OldPassword input", () => {
    // Empty old password
    cy.get("@oldPassword")
      .clear()
      .blur();
    cy.get("@oldPassword").should("have.class", "is-danger");

    // Filled old password
    cy.get("@oldPassword")
      .type("secret")
      .blur();
    cy.get("@oldPassword").not("have.class", "is-danger");
  });

  it("NewPassword input", () => {
    // Empty new password
    cy.get("@newPassword")
      .clear()
      .blur();
    cy.get("@newPassword").should("have.class", "is-danger");

    // Filled new password
    cy.get("@newPassword")
      .type("secret")
      .blur();
    cy.get("@newPassword").not("have.class", "is-danger");
  });

  it("ConfirmPassword input", () => {
    // Empty confirm password
    cy.get("@confirmPassword")
      .clear()
      .blur();
    cy.get("@confirmPassword").should("have.class", "is-danger");

    // Filled confirm password
    cy.get("@confirmPassword")
      .type("secret")
      .blur();
    cy.get("@confirmPassword").not("have.class", "is-danger");
  });

  it("Can submit", () => {
    cy.get("@oldPassword").clear();
    cy.get("@newPassword").clear();
    cy.get("@confirmPassword").clear();
    cy.get("@submit").should("be.disabled");

    cy.get("@oldPassword").type("secret");
    cy.get("@submit").should("be.disabled");
    cy.get("@oldPassword").clear();

    cy.get("@newPassword").type("secret");
    cy.get("@submit").should("be.disabled");
    cy.get("@newPassword").clear();

    cy.get("@confirmPassword").type("secret");
    cy.get("@submit").should("be.disabled");
    cy.get("@confirmPassword").clear();

    cy.get("@oldPassword").type("secret");
    cy.get("@newPassword").type("secret");
    cy.get("@confirmPassword").type("secret");
    cy.get("@submit").not("be.disabled");
  });

  it("Update", () => {
    cy.get("@oldPassword").type("secret");
    cy.get("@newPassword").type("secret");
    cy.get("@confirmPassword").type("secret");
    cy.get("@submit").click();
  });
});
