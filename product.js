// scripts/product.js

document.addEventListener("DOMContentLoaded", function() {
    const productForm = document.getElementById("productForm");
    const productId = document.getElementById("productId");
    const productName = document.getElementById("productName");
    const productDescription = document.getElementById("productDescription");
    const productPrice = document.getElementById("productPrice");
    const productQuantity = document.getElementById("productQuantity");
    const productTable = document.getElementById("productTable").getElementsByTagName("tbody")[0];

    function fetchProducts() {
        fetch("http://localhost:8080/api/estoque/produtos/listarTodos")
            .then(response => response.json())
            .then(data => {
                productTable.innerHTML = "";
                data.forEach(product => {
                    const row = productTable.insertRow();
                    row.insertCell(0).innerText = product.id;
                    row.insertCell(1).innerText = product.nome;
                    row.insertCell(2).innerText = product.descricao;
                    row.insertCell(3).innerText = product.preco;
                    row.insertCell(4).innerText = product.quantidadeEmEstoque;
                    const actions = row.insertCell(5);
                    const editButton = document.createElement("button");
                    editButton.innerText = "Edit";
                    editButton.addEventListener("click", () => {
                        productId.value = product.id;
                        productName.value = product.nome;
                        productDescription.value = product.descricao;
                        productPrice.value = product.preco;
                        productQuantity.value = product.quantidadeEmEstoque;
                    });
                    const deleteButton = document.createElement("button");
                    deleteButton.innerText = "Delete";
                    deleteButton.addEventListener("click", () => {
                        fetch(`http://localhost:8080/api/estoque/produtos/apagar/${product.id}`, {
                            method: "DELETE"
                        }).then(() => fetchProducts());
                    });
                    actions.appendChild(editButton);
                    actions.appendChild(deleteButton);
                });
            });
    }

    productForm.addEventListener("submit", function(event) {
        event.preventDefault();
        const product = {
            nome: productName.value,
            descricao: productDescription.value,
            preco: parseFloat(productPrice.value),
            quantidadeEmEstoque: parseInt(productQuantity.value)
        };
        const method = productId.value ? "PUT" : "POST";
        const url = productId.value ? `http://localhost:8080/api/estoque/produtos/atualizar` : "http://localhost:8080/api/estoque/produtos/cadastrar";
        fetch(url, {
            method: method,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(product)
        }).then(() => {
            productId.value = "";
            productName.value = "";
            productDescription.value = "";
            productPrice.value = "";
            productQuantity.value = "";
            fetchProducts();
        });
    });

    fetchProducts();
});
