const form = document.getElementById('form-atividade');
const nomes = []
const telefones = []
const userName = prompt("Qual o seu nome?")

let linhas = '';

function greetUser(){
    const insertH2 = document.getElementById('greetDiv')
    const greeting = `<h2> Olá, ${userName}, bem vindo!</h2>`
    insertH2.innerHTML = greeting
}

greetUser()

form.addEventListener('submit', function(e) {
    e.preventDefault();

    adicionaLinha()
    atualizaTabela()
})



function adicionaLinha(){
    const inputNome = document.getElementById('nome-contato');
    const inputTelefone = document.getElementById('telefone-contato')

    if(nomes.includes(inputNome.value) || telefones.includes(inputTelefone.value)) {
        alert(`O nome: ${inputNome.value} já foi inserida.`)
    } else {
        nomes.push(inputNome.value)
        telefones.push(inputTelefone.value)

        let linha = '<tr>'
        linha += `<td>${inputNome.value}</td>`
        linha += `<td>${inputTelefone.value}</td>`
        linha += '</tr>'
    
        linhas += linha
    }

    inputNome.value = ''
    inputTelefone.value = ''
}

function atualizaTabela(){
    const corpoTabela = document.querySelector('tbody')
    corpoTabela.innerHTML = linhas
}


