# Luhn Exercise

A series of methods designed to:

* Validate a card number
* Generate a check digit for a partial card number
* Calculate the number of valid card numbers withing a range

## Usage

The application can be run with the following command

```
java -jar luhnexercise-0.0.1-SNAPSHOT.jar
```
The following properties are available for adjusting the inputs either through a property file or use on the command line

```
le.cardNumber = 1358954993914435
le.partialCardNumber = 135895499391443
le.startOfCardRange = 1358954993914434
le.endOfCardRange = 1358954993914436

```
Here is an example of passing in a card number at the command line.

```
java -Dle.cardNumber=1358954993914435 -jar luhnexercise-0.0.1-SNAPSHOT.jar
