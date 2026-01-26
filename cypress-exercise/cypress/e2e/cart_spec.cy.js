describe('Cart Test', () => {

  beforeEach(() => {
    cy.visit('https://www.saucedemo.com');
    cy.get('#user-name').type('standard_user');
    cy.get('#password').type('secret_sauce');
    cy.get('#login-button').click();
  });

  it('Should add a product to the cart', () => {
    cy.get('.inventory_item').first().find('.btn_inventory').click();
    cy.get('.shopping_cart_badge').should('have.text', '1');
  });
//Remove product from cart scenario
  it('Should remove product from cart', () => {
    cy.get('.inventory_item').first().find('.btn_inventory').click();
    cy.contains('Remove').click();
    cy.get('.shopping_cart_badge').should('not.exist');
  });

  it('Should sort products by price low to high', () => {
    cy.get('.product_sort_container').select('lohi');
    cy.get('.inventory_item_price').first().should('have.text', '$7.99');
  });
//Checkout scenario
  it('Should complete checkout step one', () => {
    cy.get('.inventory_item').first().find('.btn_inventory').click();
    cy.get('.shopping_cart_link').click();
    cy.get('#checkout').click();

    cy.get('#first-name').type('John');
    cy.get('#last-name').type('Doe');
    cy.get('#postal-code').type('12345');
    cy.get('#continue').click();

    cy.url().should('include', '/checkout-step-two.html');
  });

});