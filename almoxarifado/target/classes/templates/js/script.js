const API_ITEM = "http://localhost:8080/item";
const API_CATEGORIA = "http://localhost:8080/categoria";
const API_FUNC = "http://localhost:8080/funcionarios";

async function listarItens() {
    const res = await fetch(API_ITEM);
    const data = await res.json();

    const container = document.getElementById("lista");
    container.innerHTML = "";

    data.forEach(item => {
        container.innerHTML += `
            <div class="card">
                <strong>${item.nomeItem}</strong> - Qtd: ${item.quantidade}
                <br>
                <button class="update" onclick="atualizarItem(${item.id})">Atualizar</button>
                <button class="delete" onclick="deletarItem('${item.nomeItem}')">Deletar</button>
            </div>
        `;
    });
}

async function deletarItem(nome) {
    await fetch(`${API_ITEM}/${nome}`, { method: "DELETE" });
    listarItens();
}

async function atualizarItem(id) {
    const novoNome = prompt("Novo nome:");
    await fetch(`${API_ITEM}/${id}`, {
        method: "PATCH",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(novoNome)
    });
    listarItens();
}

// ================= CATEGORIA =================

async function listarCategorias() {
    const res = await fetch(API_CATEGORIA);
    const data = await res.json();

    const container = document.getElementById("lista");
    container.innerHTML = "";

    data.forEach(cat => {
        container.innerHTML += `
            <div class="card">
                <strong>${cat.nomeCategoria}</strong>
                <br>
                <button class="delete" onclick="deletarCategoria('${cat.nomeCategoria}')">Deletar</button>
            </div>
        `;
    });
}

async function deletarCategoria(nome) {
    await fetch(`${API_CATEGORIA}/${nome}`, { method: "DELETE" });
    listarCategorias();
}

// ================= FUNCIONARIOS =================

async function listarFuncionarios() {
    const res = await fetch(API_FUNC);
    const data = await res.json();

    const container = document.getElementById("lista");
    container.innerHTML = "";

    data.forEach(func => {
        container.innerHTML += `
            <div class="card">
                <strong>${func.nomeFuncionario}</strong>
                <br>
                <button class="delete" onclick="deletarFuncionario('${func.nomeFuncionario}')">Deletar</button>
            </div>
        `;
    });
}

async function deletarFuncionario(nome) {
    await fetch(`${API_FUNC}/${nome}`, { method: "DELETE" });
    listarFuncionarios();
}