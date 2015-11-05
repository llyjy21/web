Explanation:

Main files
1.  Client: a page named index.html
    Server: index.js (routing), lacentrale.js (module), leboncoins (module)
    
2. index.js: 
    There are 3 routings, and 1 routing for testing.
    '/scrape': handle the request from a leboncoin car ad url.
    '/scrapes': handle the request from a leboncoin car search.
    'index': initilize web page.
    'test': used for test.

4. leboncoins.js: 
      using the car ad url, which comes from client, to request html page.
      get properties of this car: price, marque, modele....
        if modele is autre, try to search it from the title of this ad.
      combine all the properties into a json, return json.

3. lacentrale.js: 
    using the json which comes from the module leboncoins, seperate each parameter.
    merge those parameters into one url of lacentrale
    request this url, get the html page.
    scape the url of each search result who satisfy those conditions.
    get each price of car by requesting those urls.
    get the low, average(la cote argus), total, discription of those prices.
    merge the 4 above into a json, send it back.


Bugs:

1. scraping lacentrale hundres times in several seconds, it results to be forbid to connect to this website for minutes or hours.
Solution? Trying to use random user-agent to solve it, the effect is not obvious.

2. Foreground and background processing asynchronous problem.
    
