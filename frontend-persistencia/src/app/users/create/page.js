/* eslint-disable @next/next/no-async-client-component */
"use client";

import SubPageContainer from "@/components/SubPageContainer";
import axios from "axios";
import Button from "@/components/Button";
import { useRouter } from "next/navigation";
import { useState } from "react";

const CriarUsuarioPage = () => {
  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [bio, setBio] = useState("");
  const [rate, setRate] = useState("");
  const [dataDeNascimento, setDataDeNascimento] = useState("");

  const router = useRouter();

  async function submitHandler(event) {
    event.preventDefault();
    const payload = {
      name: nome,
      email,
      password: senha,
      bio,
      rate,
      dataDeNascimento,
    };
    await axios
      .post("http://localhost:8080/usuarios", payload)
      .then((response) => {
        if (response.status === 201) {
          router.push("/users/list");
          alert("Usuário criado com sucesso!");
        } else {
          alert("Erro ao criar usuário!");
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Erro ao criar usuário!");
      });
  }

  function handleChange(event) {
    const { name, value } = event.target;
    switch (name) {
      case "nome":
        setNome(value);
        break;
      case "email":
        setEmail(value);
        break;
      case "senha":
        setSenha(value);
        break;
      case "bio":
        setBio(value);
        break;
      case "rate":
        setRate(value);
        break;
      case "dataDeNascimento":
        setDataDeNascimento(value);
        break;
    }
  }

  return (
    <SubPageContainer
      menuTitle="Formulário de criação de usuário"
      returnCallback={() => router.push("/users")}
    >
      <form
        onSubmit={submitHandler}
        style={{ display: "flex", flexDirection: "column", width: "50%" }}
      >
        <label className="form-label" htmlFor="nome">
          Nome
        </label>
        <input
          className="form-control"
          type="text"
          id="nome"
          name="nome"
          onChange={handleChange}
          value={nome}
        />

        <label className="form-label" htmlFor="email">
          Email
        </label>
        <input
          className="form-control"
          type="email"
          id="email"
          name="email"
          onChange={handleChange}
          value={email}
        />

        <label className="form-label" htmlFor="senha">
          Senha
        </label>
        <input
          className="form-control"
          type="senha"
          id="senha"
          name="senha"
          onChange={handleChange}
          value={senha}
        />

        <label className="form-label" htmlFor="bio">
          Biografia
        </label>
        <input
          className="form-control"
          type="text"
          id="bio"
          name="bio"
          onChange={handleChange}
          value={bio}
        />

        <label className="form-label" htmlFor="rate">
          Avaliação
        </label>
        <input
          className="form-control"
          type="number"
          id="rate"
          name="rate"
          placeholder="0.0-5"
          max={5}
          min={0}
          onChange={handleChange}
          value={rate}
        />

        <label className="form-label" htmlFor="dataDeNascimento">
          Data de nascimento
        </label>
        <input
          className="form-control"
          type="date"
          id="dataDeNascimento"
          name="dataDeNascimento"
          onChange={handleChange}
          value={dataDeNascimento}
        />

        <Button
          callback={() => {}}
          textoDoBotao={"Criar Usuário"}
          type={"submit"}
          className={" mt-4 "}
        ></Button>
      </form>
    </SubPageContainer>
  );
};

export default CriarUsuarioPage;
