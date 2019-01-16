var stompClient = null;
var obj;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/websocket-rabbitmq');
    stompClient = Stomp.over(socket);
    var token = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTMzMjgyODI0LCJleHAiOjE1MzM4ODc2MjR9.hNkFtADtFZ6iAi2oBWTzsFzXW-Vj6gP1yRNIYeU_i9f45svypVjuI9lD-WtXqPviXfcRvwcRy2G6EKkKpCNaCA';
    var headers={
        'Auth-Token':$('#token').val()
    };
    stompClient.connect(headers, function (frame) {
    // stompClient.connect({}, function (frame) {
        setConnected(true);
        stompClient.subscribe('/user/topic/greeting', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
        //订阅群消息 后面的greeting 应该用群名代替
        stompClient.subscribe('/topic/greeting', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    // obj.unsubscribe();
    console.log("Disconnected");
}

var content;
function sendGroup() {
    content = $("#content").val();
    // stompClient.send("/hello", {}, JSON.stringify({'name': $("#name").val()}));
    stompClient.send("/app/toGroup", {'group':'greeting'}, JSON.stringify({'name': content}));
    $("#content").val('');
}
function sendUser() {
    content = $("#content").val();
    // stompClient.send("/user", {}, JSON.stringify({'name': $("#name").val()}));
    stompClient.send("/app/toUser", {'user': $("#name").val()}, JSON.stringify({'name': content}));
    showGreeting('我：'+content);
    $("#content").val('');
}

function subscribeUser() {
    obj = stompClient.subscribe('/user/topic/greeting', function (greeting) {
        showGreeting(JSON.parse(greeting.body).content);
    });
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    connect();
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#sub" ).click(function() { subscribeUser(); });
    $( "#send" ).click(function() { sendGroup(); });
    $( "#sendUser" ).click(function() { sendUser(); });
});

