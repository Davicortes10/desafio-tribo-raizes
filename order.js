document.addEventListener("DOMContentLoaded", function() {
    const orderForm = document.getElementById("orderForm");
    const orderUser = document.getElementById("orderUser");
    const orderProduct = document.getElementById("orderProduct");
    const orderQuantity = document.getElementById("orderQuantity");
    const orderTable = document.getElementById("orderTable").getElementsByTagName("tbody")[0];

    function fetchUsers() {
        fetch("http://localhost:8080/api/clientes")
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok: ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                console.log("Clientes fetched successfully:", data); // Log dos dados dos clientes
                orderUser.innerHTML = "";
                data.forEach(user => {
                    const option = document.createElement("option");
                    option.value = user.id;
                    option.innerText = user.nome;
                    orderUser.appendChild(option);
                });
            })
            .catch(error => console.error('There was a problem with the fetch operation (fetchUsers):', error));
    }

    function fetchProducts() {
        fetch("http://localhost:8080/api/estoque/produtos")
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok: ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                console.log("Produtos fetched successfully:", data); // Log dos dados dos produtos
                orderProduct.innerHTML = "";
                data.forEach(product => {
                    const option = document.createElement("option");
                    option.value = product.id;
                    option.innerText = `${product.nome} - ${product.preco}`;
                    orderProduct.appendChild(option);
                });
            })
            .catch(error => console.error('There was a problem with the fetch operation (fetchProducts):', error));
    }

    function fetchOrders() {
        fetch("http://localhost:8080/api/pedidos/listarPedidos")
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok: ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                console.log("Pedidos fetched successfully:", data); // Log dos dados dos pedidos
                orderTable.innerHTML = "";
                data.forEach(order => {
                    const row = orderTable.insertRow();
                    row.insertCell(0).innerText = order.id;
                    row.insertCell(1).innerText = order.clienteId;
                    row.insertCell(2).innerText = order.itensPedidos.map(item => item.produtoId).join(", ");
                    row.insertCell(3).innerText = order.itensPedidos.map(item => item.quantidade).join(", ");
                    const actions = row.insertCell(4);
                    const deleteButton = document.createElement("button");
                    deleteButton.innerText = "Delete";
                    deleteButton.addEventListener("click", () => {
                        fetch(`http://localhost:8080/api/pedidos/apagar/${order.id}`, {
                            method: "DELETE"
                        }).then(() => fetchOrders())
                        .catch(error => console.error('There was a problem with the delete operation:', error));
                    });
                    actions.appendChild(deleteButton);
                });
            })
            .catch(error => console.error('There was a problem with the fetch operation (fetchOrders):', error));
    }

    orderForm.addEventListener("submit", function(event) {
        event.preventDefault();
        const order = {
            clienteId: orderUser.value,
            itensPedidos: [
                {
                    produtoId: orderProduct.value,
                    quantidade: parseInt(orderQuantity.value)
                }
            ]
        };
        fetch(`http://localhost:8080/api/pedidos/realizarPedido?clienteId=${orderUser.value}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(order.itensPedidos)
        }).then(() => {
            fetchOrders();
        }).catch(error => console.error('There was a problem with the fetch operation (submitOrder):', error));
    });

    fetchUsers();
    fetchProducts();
    fetchOrders();
});
