class Student {
  constructor() {
    this.name = makeupName();
    this.grade = Math.floor(Math.random() * 10);
  }
}

function makeupName() {
  const itN1 = Math.floor(Math.random() * 5);
  const itN2 = Math.floor(Math.random() * 5);

  const consonants = "bcdfghjklmnpqrstvwxyz";
  const vowels = "aeiou";
  let firstName = "";
  let lastName = "";

  for (let i = 0; i < itN1; i++) {
    let c = consonants.charAt(
      Math.floor(Math.random() * consonants.length - 1)
    );
    const v = vowels.charAt(Math.floor(Math.random() * vowels.length - 1));

    i < 1 ? (c = c.toUpperCase()) : c;
    firstName += c + v;
  }

  for (let i = 0; i < itN2; i++) {
    let c = consonants.charAt(
      Math.floor(Math.random() * consonants.length - 1)
    );
    const v = vowels.charAt(Math.floor(Math.random() * vowels.length - 1));

    i < 1 ? (c = c.toUpperCase()) : c;

    lastName += c + v;
  }

  return `${firstName} ${lastName}`;
}

function genStudents(nStudents) {
  const studentArray = [];
  for (let i = 0; i < nStudents; i++) {
    const newStudent = new Student();
    studentArray.push(newStudent);
  }
  return studentArray;
}

console.log("start");

const s = new Student();
console.log(s);

const allStudents = genStudents(50);
console.log("generated students");
const filteredStudents = allStudents.filter((a) => {
  return a.grade >= 6;
});

console.log(allStudents.length);
console.log(allStudents);
console.log(filteredStudents.length);
console.log(filteredStudents);
