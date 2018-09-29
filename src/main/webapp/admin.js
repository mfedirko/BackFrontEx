// The root URL for the RESTful services
var rootURL = "http://localhost:8080/admin/users";

var currentWine;
var currentElement;
var currentRole;
var userRoleList;
// Retrieve wine list when application starts 
findAll();

// Nothing to delete in initial application state
$('#btnDelete').hide();
$('#btnRole').hide();
// Register listeners
$('#btnSearch').click(function() {
	search($('#searchKey').val());
	return false;
});


$('#linkdiv').click(function(){
	$('#linkdiv').toggleClass('linkdiv');
	
});


// Trigger search when pressing 'Return' on search key input field
$('#searchKey').keypress(function(e){
	if(e.which == 13) {
		search($('#searchKey').val());
		e.preventDefault();
		return false;
    }
});

$('#btnAdd').click(function() {
	newWine();
	return false;
});

$('#btnRole').click(function() {
	deleteRole();
	return false;
});


$('#btnSave').click(function() {
	if (currentWine.id == ''|| typeof currentWine.id == 'undefined'|| currentWine.id == 'undefined'){
		addWine();
	}
	else {
		updateWine();
	}
	
	return false;
});

$('#btnDelete').click(function() {
	if($( "#linkdiv" ).hasClass('linkdiv')){
	deleteAcct();
	}
	else {
	//	deleteWine();
	}
	
	return false;
});

$('#wineList a').live('click', function() {
	console.log('logging this a')
	console.log($(this));
	findById($(this).data('identity'));
	
});

$('#roleList a').live('click', function() {
	$('#btnRole').show();
	currentRole=$(this).data('identity');
});

//// Replace broken images with generic wine bottle
//$("img").error(function(){
//  $(this).attr("src", "pics/generic.jpg");
//
//});



function search(searchKey) {
	if (searchKey == '') 
		findAll();
	else
		findFreeForm(searchKey);
}

function newWine() {
	$('#btnDelete').hide();
	currentWine = {};
	renderDetails(currentWine); // Display empty form
}

function findAll() {
	console.log('findAll');
	$.ajax({
		type: 'GET',
		url: rootURL+'.json',
		dataType: "json", // data type of response
		success: renderList
	});
}



function findAllRoles() {
	console.log('findAllRoles');
	$.ajax({
		type: 'GET',
		url: rootURL+'/'+currentWine.id+'/role.json',
		dataType: "json", // data type of response
		success: renderRoleList,
		error: renderRoleList
	});
}




function findFreeForm(searchKey) {
	console.log('findByName: ' + searchKey);
	$.ajax({
		type: 'GET',
		url: rootURL + '/freeform/' + $('#criteria').val() +'/'+ searchKey+'.json',
		dataType: "json",
		success: renderList 
	});
}



function deleteRole() {
	console.log('deleteRole: ' + currentRole);
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/'+currentWine.id + '/role/' + currentRole+'.json',
		dataType: "json",
		success: function(data){
			$('#btnRole').hide();
			console.log('deleteRole success: ');
			findAllRoles();
		}
	});
}

function findById(id) {
	console.log('findById: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURL + '/id/' + id+'.json',
		dataType: "json",
		success: function(data){
			$('#btnDelete').show();
			
			console.log('findById success: ' + data.user.name);
			currentWine = data.user;
			renderDetails(currentWine);
			findAllRoles();
		}
	});
}

function addWine() {
	console.log('addWine');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('User created successfully');
			$('#btnDelete').show();
			if (data.accounts!=''){
				$('#acct_nb').val(data.accounts[0].acct_nb);
			}
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addWine error: ' + textStatus + ' '+errorThrown);
		}
	});
}

function updateWine() {
	console.log('updateUser');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + currentWine.id,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('User updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateWine error: ' + textStatus);
		}
	});
}

function deleteAcct() {
console.log('deleteAcct');
$.ajax({
	type: 'DELETE',
	url: 'http://localhost:8080/admin/accounts' + '/' + $('#acct_nb').val(),
	success: function(data, textStatus, jqXHR){
		alert('Account deleted successfully');
	},
	error: function(jqXHR, textStatus, errorThrown){
		alert('deleteAcct error');
	}
});
}


function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list=[];
	var list = data == null ? [] : (data instanceof Array ? data : data.userList);
	userRoleList=list;	
	$('#wineList li').remove();
	$.each(list, function(index, wine) {
		console.log('each i')
		console.log(wine);
		$('#wineList').append('<li><a href="#" data-identity="' + wine.id + '">'+wine.username+'</a></li>');
	});
}


function renderRoleList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list=[];
	var list = data == null ? [] : (data instanceof Array ? data : data.roleList);

	$('#roleList li a').remove();
	$.each(list, function(index, role) {
		$('#roleList').append('<li><a href="#" data-identity="' + role.id + '">'+role.roleName+'</a></li>');
	});
}


function renderDetails(wine) {
	console.log(wine);
	//wine=wine.user;
	$('#acct_nb').val('');
	$('#username').val(wine.username);
	$('#password').val('');
	$('#firstName').val(wine.firstName);
	$('#lastName').val(wine.lastName);
	if (wine.accounts!=''){
	$('#acct_nb').val(wine.accounts[0].acct_nb);
	}
}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	//var wineId = $('#wineId').val();
	return JSON.stringify({
		"username": $('#username').val(), 
		"password": $('#password').val(),
		"firstName": $('#firstName').val(),
		"lastName": $('#lastName').val()
		});
}