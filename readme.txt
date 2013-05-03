Notes For Grader:
Specifications to Implementation Class Map:
Component -> Investment
Leaf -> Account
Composite -> Portfolio



Specifications
Lab Deliverable 1
For this exercise you will implement a small portfolio application. A portfolio consists of accounts and other portfolios. An account is composed of securities classes:  stocks, bonds, money market. The current value of a portfolio is the sum of the current values of the different securities it contains. You will need to write software to enable a user to build a portfolio of his choice and evaluate the total value of the portfolio.

Submitting:

Submit your assignments in a folder called Deliverable1 (or similar) to the subversion repository according to the directions on the syllabus page. Place all of your Lab Deliverable 1 work into one folder. You will only be graded on your material within a reasonably labeled folder. Also, please include a README text file that contains the programming language you used. If you want to give the TAs/graders instructions on how to compile, run, or even understand your code, you can place that within the README as well.

From now on, each HW and Lab Deliverable should be submitted as described here and in HW 1. Each assignment (HW or Lab Deliverable) should have its own folder with its own README inside of it. Please ask for assistanace if you have any questions.

What you need to implement:

You could follow the design architecture suggested below or implement your own. Please remember that your design should contain a visitor (which contains the evaluation logic) an iterator (which escorts the visitor around the portfolio). In case you implement your own design, you need to describe it clearly.  In this process, you will be implementing the Composite, Visitor, and Iterator patterns.

A Component class: The base class for Composite and Leaf. 
Composite classes called Account and Portfolio are subclasses of Component, and subclasses of Leaf  that will include the classes Stock, MoneyMarket, and Bond. 

You will also need a visitor called PricingVisitor which values the different securities and sums up their current value, and an iterator called PortfolioIterator that escorts the visitor around the composite portfolio.

The Component defines the interface for all components of a portfolio. Operations to add a component, delete a component and return its current value, are part of the interface. You will also need an accept operation to allow the visitor to work.

The concrete subclasses of Component (like Account, Portfolio, Stock etc.) need to define the operations mentioned above. Composite classes like Account, contain a list of components they are made up of.  Note that this may be recursive, in that a composite may contain both leaf objects as well as other composite objects.

The PricingVisitor defines operations (e.g. visitStock, visitBond) to evaluate each kind of component.

The PortfolioIterator defines operations like first(), next(), isDone() and currItem() .

Finally you need to define a class called PortfolioManager that is a Singleton class. This would contain operations to initialize (build) a portfolio, and evaluate it. To evaluate the portfolio, the PortfolioManager would use the iterator to navigate the portfolio and the visitor to value each component.  The structure and creation of the composite structure in memory can be hardcoded in the PortfolioManager.  No client GUI is necessary.

Test your software by building a couple of portfolios and evaluating them.