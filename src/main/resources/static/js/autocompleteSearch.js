/*
 * This function is called by the library when it needs to lookup a query.
 *
 * The parameter query is the query string.
 * The doneCallback is a callback function provided by the library, after you get the
 *   suggestion list from AJAX, you need to call this function to let the library know.
 */

var searchBar = $("#autocomplete");
var prev = {};

function handleLookup(query, doneCallback) {
    console.log("autocomplete initiated");
    if (query in prev) {
        handleLookupAjaxSuccess(prev[query], query, doneCallback)
    } else {

        // TODO: if you want to check past query results first, you can do it here

        console.log("sending AJAX request to backend Java Servlet");
        // sending the HTTP GET request to the Java Servlet endpoint hero-suggestion
        // with the query data
        jQuery.ajax({
            "method": "GET",
            // generate the request url from the query.
            // escape the query string to avoid errors caused by special characters
            "url": "/suggestions?pattern=" + escape(query),
            "success": function (data) {
                // pass the data, query, and doneCallback function into the success handler
                handleLookupAjaxSuccess(data, query, doneCallback);
                // console.log("old prev", prev);

                prev[query] = data;
                // console.log("new prev", prev);
            },
            "error": function (errorData) {
                console.log("lookup ajax error");
                console.log(errorData)
            }
        })

    }
}


/*
 * This function is used to handle the ajax success callback function.
 * It is called by our own code upon the success of the AJAX request
 *
 * data is the JSON data string you get from your Java Servlet
 *
 */
function handleLookupAjaxSuccess(data, query, doneCallback) {
    console.log("lookup ajax successful");

    // parse the string into JSON
    // var jsonData = JSON.parse(data);
    console.log(data);

    // TODO: if you want to cache the result into a global variable you can do it here

    // call the callback function provided by the autocomplete library
    // add "{suggestions: jsonData}" to satisfy the library response format according to
    //   the "Response Format" section in documentation
    doneCallback({suggestions: data});
}


/*
 * This function is the select suggestion hanlder function.
 * When a suggestion is selected, this function is called by the library.
 *
 * You can redirect to the page you want using the suggestion data.
 */
function handleSelectSuggestion(suggestion) {
    // TODO: jump to the specific result page based on the selected suggestion

    var url = suggestion["data"]["type"] + "?id=" + suggestion["data"]["id"];
    console.log(url);
    window.location.href = url;
}


/*
 * This statement binds the autocomplete library with the input box element and
 *   sets necessary parameters of the library.
 *
 * The library documentation can be find here:
 *   https://github.com/devbridge/jQuery-Autocomplete
 *   https://www.devbridge.com/sourcery/components/jquery-autocomplete/
 *
 */
// $('#autocomplete') is to find element by the ID "autocomplete"
searchBar.autocomplete({
    // documentation of the lookup function can be found under the "Custom lookup function" section
    lookup: function (query, doneCallback) {
        handleLookup(query, doneCallback)
    },
    onSelect: function (suggestions) {
        handleSelectSuggestion(suggestions)
    },
    // set the groupby name in the response json data field
    groupBy: "type",
    minChars: 3,
    // set delay time
    deferRequestBy: 300
    // there are some other parameters that you might want to use to satisfy all the requirements
    // TODO: add other parameters, such as mininum characters
});


/*
 * do normal full text search if no suggestion is selected
 */
function handleNormalSearch(query) {
    console.log("doing normal search with query: " + query);
    // TODO: you should do normal search here
}

// bind pressing enter key to a hanlder function
searchBar.keypress(function (event) {
    // keyCode 13 is the enter key
    if (event.keyCode === 13) {
        // pass the value of the input box to the hanlder function
        handleNormalSearch(searchBar.val())
    }
});