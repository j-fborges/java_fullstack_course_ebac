import { useState } from "react";

import "./main.css";

function App() {
  const [weight, setWeight] = useState(0);
  const [height, setHeight] = useState(0);
  const [cmi, setCMI] = useState(undefined);
  const [cmiClass, setCMIClass] = useState(undefined);

  function getCMI() {
    const _cmi = weight / ((height / 100) * (height / 100));
    setCMI(_cmi);
    setCMIClass(classifyCMI(_cmi));
  }

  function classifyCMI(_cmi) {
    if (_cmi < 18.5) return "MAGREZA";
    else if (_cmi > 18.5 && _cmi < 24.9) return "NORMAL";
    else if (_cmi > 25 && _cmi < 29.9) return "SOBREPESO";
    else if (_cmi > 30 && _cmi < 39.9) return "OBESIDADE";
    else if (_cmi > 40) return "OBESIDADE GRAVE";
    else return "-";
  }

  return (
    <div className="flex flex-col w-[100vw] h-[100vh] items-center justify-center ">
      <div className="flex flex-col items-center gap-6 w-[100%] max-w-[512px] p-4 bg-[#999] rounded-3xl">
        <h1 className="text-2xl font-bold">Calcule o IMC</h1>
        <form
          action=""
          className="flex flex-col items-center justify-center gap-4 w-[100%]"
        >
          <div className="flex flex-row gap-4">
            <label htmlFor="weight" className="text-lg font-bold">
              Peso (kg):
            </label>
            <input
              className="bg-white w-[30%] text-black pl-4"
              type="number"
              name="weight"
              id="weight"
              onKeyUp={(e) => {
                setWeight(parseFloat(e.target.value));
              }}
            />
          </div>
          <div className="flex flex-row gap-4 ">
            <label htmlFor="height" className="text-lg font-bold">
              Altura (cm):
            </label>
            <input
              className="bg-white w-[30%] text-black pl-4"
              type="number"
              name="height"
              id="height"
              onKeyUp={(e) => {
                setHeight(parseFloat(e.target.value));
              }}
            />
          </div>
          <button
            type="button"
            className="px-4 py-2 bg-[#eee] text-lg text-black font-bold rounded-2xl"
            onClick={() => getCMI()}
          >
            Calcular
          </button>
        </form>

        <div className="flex flex-col">
          <h2 className="text-lg font-bold">{`IMC: ${cmi ? cmi : "--"}`}</h2>
          <h2 className="text-lg font-bold">{`Diagnóstico: ${
            cmiClass ? cmiClass : "--"
          }`}</h2>
        </div>
      </div>
    </div>
  );
}

export default App;
