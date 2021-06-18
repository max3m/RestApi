$(document).ready(() => {
    getAllUsers();
})

function getAllUsers() {
    const url = 'http://localhost:8080/api/users';
    const container = $('#tbody');
    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((response) => {

        let html = '';

        response.forEach((item) => {
            let trHtml =
                `<tr data-id="${item.id}">
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.lastname}</td>
                    <td>${item.age}</td>
                    <td>${item.email}</td>
                    <td>${item.roles}</td>
                    <td><a id="buttonEditUser" role="button" onclick="getEditModal(${item.id})" class="btn btn-primary btn-sm" data-target="#buttonEditUser">Edit</a></td>
                    <td><a id="buttonDeleteUser" role="button" onclick="getDeleteModal(${item.id})" class="btn btn-danger btn-sm" >Delete</a></td>
                </tr>`;
            html += trHtml;
        })

        container.html(html);
    })
}

function getUser(id) {
    const url = 'http://localhost:8080/api/users/' + id;
    const container = $('#tbody');
    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((response) => {
        let item = response;

        let trHtml =
            `
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.lastname}</td>
                    <td>${item.age}</td>
                    <td>${item.email}</td>
                    <td>${item.roles}</td>
                    <td><a id="buttonEditUser" role="button" onclick="getEditModal(${item.id})" class="btn btn-primary btn-sm" data-target="#buttonEditUser">Edit</a></td>
                    <td><a id="buttonDeleteUser" role="button" onclick="getDeleteModal(${item.id})" class="btn btn-danger btn-sm" >Delete</a></td>
                `;
        container.find(`tr[data-id=${item.id}]`).html(trHtml)
    })
}

function addUser() {
    let user = {
        name: $('#new_user #firstname').val(),
        lastname: $('#new_user #lastname').val(),
        age: $('#new_user #age').val(),
        email: $('#new_user #email').val(),
        password: $('#new_user #password').val(),
        roles: $('#addRoleList').val()
    }

    $.ajax({
            url: 'http://localhost:8080/api/users',
            type: 'POST',
            contentType: 'application/json',
            processData: false,
            cash: false,
            data: JSON.stringify(user)
        }
    ).done(function () {
        getAllUsers();
        $('.ustable').click()
    })
}

function getEditModal(id) {
    $.ajax({
        url: 'http://localhost:8080/api/users/' + id,
        dataType: 'json',
        type: 'GET'
    }).done(user => {
        let html = document.getElementById("modalEdit");
        let roles = ['Admin', 'User'];

        html.innerHTML = `<div id="edit" class="modal fade" tabIndex="-1" role="dialog" 
                              aria-labelledby="TitleModalLabel" aria-hidden="true"
                              data-backdrop="static" data-keyboard="false">
            <div class="modal-dialog modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Edit user</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="formEditUser" 
                              class="form-signin mx-auto font-weight-bold text-center"
                              style="width: 200px;">
                            <p>
                                <label for="del_id">ID</label>
                                <input class="form-control form-control-sm" type="text" name="id" id="del_id"
                                       readOnly value="${user.id}">
                            </p>
                            <p><label for="name">Name</label>
                                <input type="text" name="name" value="${user.name}" id="name"></p>
                            <p><label for="lastname">Lastname</label>
                                <input type="text" name="lastname" value="${user.lastname}" id="lastname"></p>
                            <p><label for="Password">Password</label>
                                <input type="password" name="password" value="${user.password}" id="Password"></p>
                            <p><label for="age">Age</label>
                                <input type="number" name="age" value="${user.age}" id="age"></p>
                            <p><label for="email">Email</label>
                                <input type="text" name="email" value="${user.email}" id="email"></p>
                            <p>
                            <label>Role</label>
                                <select id="roles" multiple size="2" required
                                               class="form-control form-control-sm">
                                        <option value="ADMIN">ADMIN</option>
                                        <option value="USER">USER</option>
                                </select>
                            </p>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                                    </button>
                                    <button type="button" 
                                            class="btn btn-primary js-edit-user">Edit
                                    </button>
                                </div>
                                </form>
                    </div>
                </div>
            </div>
        </div>`
        $('#edit').modal();
        $('.js-edit-user').click(function () {
            editUser(user);
        })
    })

    function editUser(user) {
        const form = document.getElementById('formEditUser');
        const formData = new FormData(form);
        console.log(form);
        const data = {
            roles: $('#roles').val()
        };
        for (let key of formData.keys()) {
            data[key] = formData.get(key);
        }

        $.ajax({
                url: 'http://localhost:8080/api/users/' +id,
                type: 'PUT',
                contentType: 'application/json',
                processData: false,
                cash: false,
                data: JSON.stringify(data)
            }
        ).always(function () {
            getUser(id);
            $('#edit').modal('hide');
        })
    }
}
function getDeleteModal(id) {
    $.ajax({
        url: 'http://localhost:8080/api/users/' + id,
        dataType: 'json',
        type: 'GET'
    }).done(user => {
        let adminSelect = "";
        let userSelect = "";

        let html = document.getElementById("modalEdit");
        html.innerHTML = `<div id="edit" class="modal fade" tabIndex="-1" role="dialog" 
                              aria-labelledby="TitleModalLabel" aria-hidden="true"
                              data-backdrop="static" data-keyboard="false">
            <div class="modal-dialog modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Delete user</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="formDeleteUser" 
                              class="form-signin mx-auto font-weight-bold text-center"
                              style="width: 200px;">
                            <p><label for="del_id">ID</label>
                                <input class="form-control form-control-sm" type="text" name="id" id="del_id"
                                readOnly value="${user.id}"></p>
                            <p><label for="name">Name</label>
                                <input class="form-control form-control-sm" type="text" name="name" id="name"
                                readonly value="${user.name}"></p>
                            <p><label for="lastname">Lastname</label>
                                <input class="form-control form-control-sm" type="text" name="lastname" id="lastname"
                                readonly value="${user.lastname}"></p>
                            <p><label for="age">Age</label>
                                <input class="form-control form-control-sm" type="number" name="age" id="age" 
                                readOnly value="${user.age}"></p>
                            <p><label for="email">Email</label>
                                <input class="form-control form-control-sm" type="text" name="email" id="email"
                                readOnly value="${user.email}"></p>
                            <p><label for="roles">Roles</label>
                                <input class="form-control form-control-sm" type="text" name="roles" id="roles"
                                readOnly value="${user.roles}"></p>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-danger js-delete-user">Delete</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>`
        $('#edit').modal();
        $('.js-delete-user').click(function () {
            deleteUser();
        })
    })

    function deleteUser() {
        const form = document.getElementById('formDeleteUser');
        const formData = new FormData(form);
        const data = {};

        for (let key of formData.keys()) {
            data[key] = formData.get(key);
        }
        const id = data.id

        $.ajax({
                url: 'http://localhost:8080/api/users/' + id,
                type: 'DELETE',
                contentType: 'application/json',
                processData: false,
                cash: false,
                data: JSON.stringify(data)
            }
        ).always(function () {
            $(`tr[data-id=${data.id}]`).remove()
            $('#edit').modal('hide');
        })
    }
}
