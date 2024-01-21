@Tag
Feature: One way flight booking from Goa to Pune

  #this executes before each scenario - like before method.
  Background:
    Given Come to sites landing page

  Scenario Outline: Pune to Goa one way flight booking through Air India
    Given Fill flight travel ralated details <from> <to> and date details
    When Select Flight <flightname> and click view prices dropdown and book a comfort flight
    When Fill personal details of the traveller
    | 9067827802               |
    | richarddsouza@gmail.com  |
    | 403517                   |
    | Goa                      |
    | Newtons Villa Siolim Goa |
    When Select Free or Paid Seats for the flights for the trip
    Then Final confirmation page displays with proceed to pay button
    Examples:
      | from  | to   | flightname |
      | Goa   | Pune | Air India   |

  @special
  Scenario: Enter Flight Search Details And Forcefully Fail
    Given Fill flight travel related details and date details
    Then Shows listing of listing of flights available
