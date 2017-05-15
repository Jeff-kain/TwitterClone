//output for logging
var output = null;
//WebSocket placeholder
var websocket = null;

function getWsUri() {
    var wsUriSuffix = "echo-socket";
    var uri = document.URL.replace("http", "ws").replace("public/start.xhtml", wsUriSuffix);
    return uri.match(wsUriSuffix) ? uri : uri + wsUriSuffix;
}


function connect() {
    //construct ws URI
    var wsURI = getWsUri();
    //create socket
    websocket = new WebSocket(wsURI);
    //atach event listeneres
    websocket.onopen = function () {
        log('blue', 'CONNECTED');
        // doSend("WebSockets rock");
    };
    websocket.onclose = function () {
        log('blue', 'DISCONNECTED');
    };
    websocket.onmessage = function (evt) {
        //convert json to javascript object
        var message = JSON.parse(evt.data);
        //write message.text to screen
        log('green', 'I: ' + message.text);
        refresh();
    };
    websocket.onerror = function (event) {
        log('red', 'ERROR: ' + event.data);
    };
}

function onLoad() {
    output = document.getElementById("output");
    connect();
}

function doSend(string) {
    var message = JSON.stringify({'text': string});
    log('black', 'O: ' + string);
    websocket.send(message);
}

//appends html to #output
function log(colour, message) {
    output.innerHTML += "<span style='color: " + colour + "'>" + message + "</span><br>";
}

function clearConsole() {
    output.innerHTML = '';
}

//invoke init() on load
window.addEventListener("load", onLoad, false);

//enter key clicks #sendButton
function keyPressed(event) {
    if (event.keyCode === 13) {
        document.getElementById("sendButton").click();
        document.getElementById("textforws").value = "";
    }
}

function refresh() {
    location.reload(true);
}


