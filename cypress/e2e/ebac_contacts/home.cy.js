/// <reference types="cypress" />

describe("Tests for EBAC's Contact List app", () => {
  beforeEach(() => {
    cy.visit("https://ebac-agenda-contatos-tan.vercel.app/");
  });

  it("Should load the page propperly", () => {
    cy.contains("Agenda de contatos");
    cy.get(".sc-gLDzan.ckeKmo > input").should("have.length", 3);
  });

  it("Should add a Contact to the Contact List", () => {
    const newContactName = "Foo Bar Test";

    cy.get(".sc-beqWaB.eQdhbg.contato").its("length").as("initialLength");

    cy.get("input[type=text]").type(newContactName);
    cy.get("input[type=email]").type("foo@bar.com");
    cy.get("input[type=tel]").type("99998888");
    cy.get(".adicionar").click();

    cy.get("@initialLength").then((initialLength) => {
      cy.get(".sc-beqWaB.eQdhbg.contato").should(
        "have.length",
        initialLength + 1
      );
    });
    cy.get(".sc-beqWaB.eQdhbg.contato")
      .last()
      .should("contain", newContactName);
  });

  it("Should edit a Contact from the Contact List", () => {
    cy.get(".sc-beqWaB.eQdhbg.contato").its("length").as("initialLength");

    const editContactName = "John Johnny";
    cy.get(".sc-beqWaB.eQdhbg.contato").last().find(".edit").click();

    cy.get("input[type=text]").clear().type(editContactName);
    cy.get("input[type=email]").clear().type("john@johnny.com");
    cy.get("input[type=tel]").clear().type("777766666");

    cy.get(".alterar").click();

    cy.get("@initialLength").then((initialLength) => {
      cy.get(".sc-beqWaB.eQdhbg.contato").should("have.length", initialLength);
    });

    cy.get(".sc-beqWaB.eQdhbg.contato")
      .last()
      .should("contain", editContactName);
  });

  it("Should remove a Contact from the Contact List", () => {
    cy.get(".sc-beqWaB.eQdhbg.contato").its("length").as("initialLength");
    cy.get(".sc-beqWaB.eQdhbg.contato").last().find(".delete").click();

    cy.get("@initialLength").then((initialLength) => {
      cy.get(".sc-beqWaB.eQdhbg.contato").should(
        "have.length",
        initialLength - 1
      );
    });
  });
});
