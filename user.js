document.addEventListener("DOMContentLoaded", function() {
    const userForm = document.getElementById("userForm");
    const userId = document.getElementById("userId");
    const userName = document.getElementById("userName");
    const userCpf = document.getElementById("userCpf");
    const userEmail = document.getElementById("userEmail");
    const userPhone = document.getElementById("userPhone");
    const userAddress = document.getElementById("userAddress");
    const userTable = document.getElementById("userTable").getElementsByTagName("tbody")[0];

    function fetchUsers() {
        fetch("http://localhost:8080/api/clientes/listarTodos")
            .then(response => response.json())
            .then(data => {
                userTable.innerHTML = "";
                data.forEach(user => {
                    const row = userTable.insertRow();
                    row.insertCell(0).innerText = user.id;
                    row.insertCell(1).innerText = user.nome;
                    row.insertCell(2).innerText = user.cpf;
                    row.insertCell(3).innerText = user.email;
                    row.insertCell(4).innerText = user.telefone;
                    row.insertCell(5).innerText = user.endereco;
                    const actions = row.insertCell(6);
                    const editButton = document.createElement("button");
                    editButton.innerText = "Edit";
                    editButton.addEventListener("click", () => {
                        userId.value = user.id;
                        userName.value = user.nome;
                        userCpf.value = user.cpf;
                        userEmail.value = user.email;
                        userPhone.value = user.telefone;
                        userAddress.value = user.endereco;
                    });
                    const deleteButton = document.createElement("button");
                    deleteButton.innerText = "Delete";
                    deleteButton.addEventListener("click", () => {
                        fetch(`http://localhost:8080/api/clientes/apagar/${user.id}`, {
                            method: "DELETE"
                        }).then(() => fetchUsers());
                    });
                    actions.appendChild(editButton);
                    actions.appendChild(deleteButton);
                });
            });
    }

    userForm.addEventListener("submit", function(event) {
        event.preventDefault();
        const user = {
            id: userId.value,
            nome: userName.value,
            cpf: userCpf.value,
            email: userEmail.value,
            telefone: userPhone.value,
            endereco: userAddress.value
        };
        const method = userId.value ? "PUT" : "POST";
        const url = userId.value ? `http://localhost:8080/api/clientes/atualizar` : "http://localhost:8080/api/clientes/cadastrar";
        fetch(url, {
            method: method,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        }).then(() => {
            userId.value = "";
            userName.value = "";
            userCpf.value = "";
            userEmail.value = "";
            userPhone.value = "";
            userAddress.value = "";
            fetchUsers();
        });
    });

    fetchUsers();
});
