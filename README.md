I found this [React Native + Redux](https://medium.com/react-native-training/bitcoin-ripple-ethereum-price-checker-with-react-native-redux-e9d076037092)
tutorial online, and decided to encorporate the idea into an Android kotlin application instead.
From the tutorial, I took the basic layout of the RecyclerList cells, and the image assets provided.

To improve the application, I added functionality for on item clicked to display more detailed
information, and added a section for Global Stats.
# CryptoFetcher

CryptoFetcher is an Android application written in Kotlin.
It uses Retrofit2 to fetch details from
the [CoinMarket](https://coinmarketcap.com/api/) api.

The app's main screen contains a RecyclerList View of `CryptoResponse` objects, which represent the
results of a call to the API.
The default request returns a ranked list of the top 100 crypto-currencies available on the market.
However retrofit gives the ability to change the query parameters, such as currency and list size.

When a list item is clicked, it shows a more in depth description of the currency's stats.

In the app's action bar, there is an option to view the Global stats.
These stats are about the global data set, such as the active markets, currencies, total market cap etc.


Finally there is another action that links the user to the Zendesk help center of the app.
This allows the user to visit FAQ's of the app and ask any questions about the app, towards the dev.

## CryptoResponse
The CryptoResponse data class represents the response to the /ticker/ endpoint of the API.
Each instance of a CryptoResponse object represents an individual crypto-currency.

Information provided about the crypto-currency:
 - The symbol and name. "BTC, Bitcoin"
 - The rank of each coin by market cap
 - The price of the coin in USD
 - The price of the coin in bitcoin
 - The market cap
 - The total and available supply
 - The percentage change within the last hour/day/week
 - When it was last updated

Retrofit creates an implementation of the `CryptoService`
interface which hits the API endpoints.

### Further improvements
- [ ] Add sort functionality
- [ ] Add search functionality
- [ ] Change currency functionality
- [ ] Move stats into a fragment, and make a one page activity with tabs.
