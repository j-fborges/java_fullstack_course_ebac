document.addEventListener("DOMContentLoaded", function () {
  fetch("https://api.github.com/users/j-fborges")
    .then(function (resposta) {
      return resposta.json();
    })
    .then(function (json) {
      const name = json.name;
      const githubLink = json.html_url;
      const login = json.login;
      const following = json.following;
      const followers = json.followers;
      const avatarUrl = json.avatar_url;
      const reposN = json.public_repos;
      document.getElementById("name").innerHTML = name;
      document.getElementById("username").innerHTML = login;
      document.getElementById("repos").innerHTML = reposN;
      document.getElementById("following").innerHTML = following;
      document.getElementById("followers").innerHTML = followers;
      document.getElementById("githubLink").href = githubLink;
      document.getElementById("avatar").src = avatarUrl;
    })
    .catch(function (error) {
      alert("Something went wrong!");
      console.log(error);
    });
});
