const url = 'http://localhost:8080';
let stompClient;
let selectedUser;
let newMessages = new Map();


function connectToChat(userName) {
	
	console.log(userName+"in connect")
    console.log("connecting to chat...")
    let socket = new SockJS(url + '/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to: " + frame);
        stompClient.subscribe("/topic/messages/" + userName, function (response) {
            let data = JSON.parse(response.body);
            if (selectedUser === data.fromLogin) {
                render(data.message, data.fromLogin);
            } else {
                newMessages.set(data.fromLogin, data.message);
                console.log(data.message+"in else");
                $('#userNameAppender_' + data.fromLogin).append('<span id="newMessage_' + data.fromLogin + '" style="color: red">New</span>');
            }
        });
    });
}

function sendMsg(from, text) {
    stompClient.send("/app/chat/" + selectedUser, {}, JSON.stringify({
        fromLogin: from,
        message: text
    }));
}

function registration() {
    let userName = document.getElementById("userName").value;
    console.log(userName+"in reg");
    connectToChat(userName);
}

function selectUser(userName) {
    console.log("selecting users: " + userName);
    selectedUser = userName;
    let isNew = document.getElementById("newMessage_" + userName) !== null;
    console.log("is new"+isNew);
    if (isNew) {
        let element = document.getElementById("newMessage_" + userName);
        element.parentNode.removeChild(element);
        render(newMessages.get(userName), userName);
    }
    $('#selectedUserId').html('');
    $('#selectedUserId').append('Chat with ' + userName);
}

/*function findEmail()
{
	let windowUrl=window.location.href;
	let userNameElement=document.getElementById("userName");
	let arr=windowUrl.split("=");
	userNameElement.value=arr[1];
	
	document.getElementById("userEmail").textContent=arr[1];
	
	findEmail1();
	
}*/

function findEmail()
{
	let windowUrl=window.location.href;
	let arr=windowUrl.split("=");
	let userNameElement=document.getElementById("userName");
	let userEmailElement=document.getElementById("userEmail");
	$.get(url+"/findEmail1/"+arr[1],function(response){
		
		let user=response;
		userNameElement.value=user.name;
		userEmailElement.textContent=user.email;
		console.log(response);
		console.log(document.getElementById("userName").value);
		registration();
		fetchAll();
	});
	//console.log(document.getElementById("userName").value);
}

function fetchAll() {
	//console.log(window.location.href);
	let userEmail=document.getElementById("userEmail").textContent;
    $.get(url + "/fetchAllUsers/"+userEmail, function (response) {
        let users = response;
        let usersTemplateHTML = "";
        for (let i = 0; i < users.length; i++) {
            usersTemplateHTML = usersTemplateHTML + '<a href="#" onclick="selectUser(\'' + users[i].name + '\')"><li class="clearfix">\n' +
                '                <img src="user.png" width="55px" height="55px" alt="avatar" />\n' +
                '                <div class="about">\n' +
                '                    <div id="userNameAppender_' + users[i].name + '" class="name" style="color:orange;">' + users[i].name + '</div>\n' +
                '                    <div class="status">\n' +
                '                        <i class="fa fa-circle offline"></i>\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '            </li></a>';
        }
        $('#usersList').html(usersTemplateHTML);
    });
}