function getPrincipal(){
    const url = 'http://localhost:8080/api/principal';
    const container = $('#usertbody');
    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((response) => {
        console.log(response)

        let html = '';

        let trHtml =
            `<tr>
                    <td>${response.id}</td>
                    <td>${response.name}</td>
                    <td>${response.lastname}</td>
                    <td>${response.age}</td>
                    <td>${response.email}</td>
                    <td>${response.roles}</td>
                </tr>`;
        html += trHtml;
        container.html(html);
    })
}
function userHeader(){
    fetch('http://localhost:8080/api/principal')
        .then(response => response.json())
        .then(user => {
            document.getElementById('headerEmail').innerHTML = user.email;
            document.getElementById('headerRoles').innerHTML = 'with roles ' + user.roles;
        })
}