import { v4 as uuidv4 } from "uuid";

describe("Organization Manage User", () => {
  before(() => {
    cy.login("admin");
    cy.createOrganization();
    cy.saveLocalStorage();
  });

  afterEach(() => {
    cy.reload();
  });

  describe("Add a member", () => {
    beforeEach(() => {
      cy.restoreLocalStorage().then(() => {
        cy.visit(
          `/organization-manage/${localStorage.getItem(
            "organizationId"
          )}/members`
        );
        cy.get("#OrganizationTemplateList-add").click();
        cy.get("input#OrganizationOrganizationMembersAdd-email.input").as(
          "email"
        );
        cy.get("button#TemplateSidePanelRight-confirm").as("confirm");
      });
    });

    afterEach(() => {
      cy.reload();
    });

    it("Open panel", () => {
      cy.get("#TemplateSidePanelRight").should("exist");
    });

    it("Email input", () => {
      cy.get("@email").should("be.empty");

      cy.get("@email")
        .type("badEmail")
        .blur();
      cy.get("@email").should("have.class", "is-danger");

      cy.get("@email")
        .clear()
        .blur();
      cy.get("@email").should("have.class", "is-danger");

      cy.get("@email")
        .type("good@email.com")
        .blur();
      cy.get("@email").not("have.class", "is-danger");
    });

    it("Create new user", () => {
      cy.get("@confirm").should("be.disabled");

      cy.get("@email").type(`${uuidv4()}@meember.com`);

      cy.get("@confirm").not("be.disabled");
      cy.get("@confirm").click();

      cy.wait(1000);

      cy.get("input#OrganizationOrganizationMembers-firstName.input").as(
        "firstName"
      );
      cy.get("input#OrganizationOrganizationMembers-lastName.input").as(
        "lastName"
      );

      cy.get("@firstName").should("be.empty");
      cy.get("@lastName").should("be.empty");

      cy.get("@confirm").should("be.disabled");

      cy.get("@firstName")
        .click()
        .blur();
      cy.get("@firstName").should("have.class", "is-danger");

      cy.get("@lastName")
        .click()
        .blur();
      cy.get("@lastName").should("have.class", "is-danger");

      cy.get("@firstName")
        .type(`${uuidv4()}-firstName`)
        .blur();
      cy.get("@firstName").not("have.class", "is-danger");

      cy.get("@lastName")
        .type(`${uuidv4()}-lastName`)
        .blur();
      cy.get("@lastName").not("have.class", "is-danger");

      cy.get("@confirm").not("be.disabled");

      cy.get("@confirm").click();
      cy.get("#TemplateSidePanelRight").should("not.exist");
    });
  });

  it("Change right", () => {
    cy.restoreLocalStorage().then(() => {
      cy.request({
        url: `http://localhost:8000/test/register`,
        method: "POST",
        body: {
          email: `${uuidv4()}-member@meember.com`,
          password: "secret"
        }
      })
        .its("body")
        .then(body => {
          cy.request({
            url: "http://localhost:8000/member/",
            method: "POST",
            body: {
              idOrganization: localStorage.getItem("organizationId"),
              idUser: body.id
            },
            auth: {
              bearer: `${localStorage.getItem("token")}`
            }
          })
            .its("body")
            .then(body => {
              cy.visit(
                `/organization-manage/${localStorage.getItem(
                  "organizationId"
                )}/members`
              );
              cy.wait(10000);
              cy.get(`#OrganizationMembersDropdown-${body.id}`).click();

              cy.get("#OrganizationMembersDropdown-right").click({
                force: true
              });

              cy.get("#OrganizationMembersRight-owner").as("owner");
              cy.get("#OrganizationMembersRight-officeInsctructor").as(
                "officeInsctructor"
              );
              cy.get("#OrganizationMembersRight-office").as("office");
              cy.get("#OrganizationMembersRight-instructor").as("instructor");
              cy.get("#OrganizationMembersRight-member").as("member");

              cy.get("@member").should("have.class", "is-selected");

              cy.get("@owner").click();
              cy.get("@owner").should("have.class", "is-selected");

              cy.get("@officeInsctructor").click();
              cy.get("@officeInsctructor").should("have.class", "is-selected");

              cy.get("@office").click();
              cy.get("@office").should("have.class", "is-selected");

              cy.get("@instructor").click();
              cy.get("@instructor").should("have.class", "is-selected");

              cy.get("@member").click();
              cy.get("@member").should("have.class", "is-selected");

              cy.get("#TemplateModal-confirm").click();

              cy.wait(100);

              cy.get("#OrganizationMembersRight-error").should("not.exist");
              cy.get("#TemplateModal").should("not.exist");
            });
        });
    });
  });
});
