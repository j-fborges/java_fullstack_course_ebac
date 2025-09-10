function Animal(_name, _species, _putsEggs){
    this.name = _name
    this.species = _species
    this.putsEggs = _putsEggs
}

function Mammal(_name, _species){
    Animal.call(this, _name, _species, false)
}

function Reptile(_name, _species){
    Animal.call(this, _name, _species)
    this.putsEggs = true
}

const a = new Mammal("Lobo Guará", "Chrysocyon brachyurus")
const b = new Reptile("Teiú Gigante", "Salvator Merianae")
const c = new Animal("Jataí Amarela", "T. Angustula", true)

console.log("Hello world!")
console.log(a)
console.log(b)
console.log(c)