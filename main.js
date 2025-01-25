const form1 = document.getElementById("form-test");
const form = document.getElementById("form-deposito");
const nomeBeneficiario = document.getElementById("nome-beneficiario");
let formEValido = false;
let isValid = false;

const valorA = document.getElementById("test-a");
const valorB = document.getElementById("test-b");
const mensagemSucesso1 = `Sim, B > A ! `;

const containerMensagemSucesso = document.querySelector(".success-message1");

function validaValor() {
  return Number(valorB.value) > Number(valorA.value);
}

function validaNome(nomeCompleto) {
  const nomeComoArray = nomeCompleto.split(" ");
  return nomeComoArray.length >= 2;
}
form1.addEventListener("submit", function (e) {
  e.preventDefault();

  isValid = validaValor();
  console.log(`isValid: ${isValid}`);

  if (isValid) {
    containerMensagemSucesso.innerHTML = mensagemSucesso1;
    containerMensagemSucesso.style.display = "block";
    containerMensagemSucesso.style.backgroundColor = "green";

    valorA.value = "";
    valorB.value = "";
    valorA.classList.remove("error");
  } else {
    valorA.style.border = "1px solid red";
    document.querySelector(".error-message1").style.display = "block";
  }
});

valorA.addEventListener("keyup", function (e) {
  e.preventDefault();
  console.log(e.target.value);
  containerMensagemSucesso.style.display = "none";
  isValid = validaValor();

  if (!isValid) {
    valorA.classList.add("error");
    document.querySelector(".error-message1").style.display = "block";
  } else {
    document.querySelector(".error-message1").style.display = "none";
    valorA.classList.remove("error");
  }
});

valorB.addEventListener("keyup", function (e) {
  e.preventDefault();
  containerMensagemSucesso.style.display = "none";
  console.log(e.target.value);
  isValid = validaValor();

  if (!isValid) {
    valorA.classList.add("error");
    document.querySelector(".error-message1").style.display = "block";
  } else {
    document.querySelector(".error-message1").style.display = "none";
    valorA.classList.remove("error");
  }
});

form.addEventListener("submit", function (e) {
  e.preventDefault();

  const numeroContaBeneficiario = document.getElementById("numero-conta");
  const valorDeposito = document.getElementById("valor-deposito");
  const mensagemSucesso = `Montante de: ${valorDeposito.value} depositado para o cliente: ${nomeBeneficiario.value} - conta: ${numeroContaBeneficiario.value}`;
  const description = document.getElementById("descricao");

  formEValido = validaNome(nomeBeneficiario.value);
  if (formEValido) {
    const containerMensagemSucesso = document.querySelector(".success-message");
    containerMensagemSucesso.innerHTML = mensagemSucesso;
    containerMensagemSucesso.style.display = "block";
    containerMensagemSucesso.style.backgroundColor = "green";

    // alert(mensagemSucesso);

    nomeBeneficiario.value = "";
    numeroContaBeneficiario.value = "";
    valorDeposito.value = "";
    description.value = "";
  } else {
    // alert("o nome não está completo")
    nomeBeneficiario.style.border = "1px solid red";
    document.querySelector(".error-message").style.display = "block";
  }
});

nomeBeneficiario.addEventListener("keyup", function (e) {
  console.log(e.target.value);
  formEValido = validaNome(e.target.value);

  if (!formEValido) {
    nomeBeneficiario.classList.add("error");
    document.querySelector(".error-message").style.display = "block";
  } else {
    nomeBeneficiario.classList.remove("error");
    document.querySelector(".error-message").style.display = "none";
  }
});
