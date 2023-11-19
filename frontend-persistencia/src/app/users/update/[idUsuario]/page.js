"use client";
import Button from "@/components/Button";
import SubPageContainer from "@/components/SubPageContainer";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const UpdateUsuarioPage = ({ params }) => {
  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [bio, setBio] = useState("");
  const [rate, setRate] = useState("");
  const [dataDeNascimento, setDataDeNascimento] = useState("");
  const [usuario, setUsuario] = useState(null);
  const { idUsuario } = params;
  const router = new useRouter();

  useEffect(() => {
    async function getUsuario() {
      if (usuario !== null) {
        return;
      }

      const response = await axios
        .get(`http://localhost:8080/usuarios/${idUsuario}`)
        .then((response) => response.data);

      if (response === null) {
        alert("Usuário não encontrado!");
        router.push("/users/list");
      }
      setNome(response.name);
      setEmail(response.email);
      setSenha(response.password);
      setBio(response.bio);
      setRate(response.rate);
      const dataDeNascimentoLocal = new Date(response.dataDeNascimento);
      setDataDeNascimento(
        `${dataDeNascimentoLocal.getFullYear()}-${
          dataDeNascimentoLocal.getMonth() < 10
            ? "0" + dataDeNascimentoLocal.getMonth()
            : dataDeNascimentoLocal.getMonth()
        }-${
          dataDeNascimentoLocal.getDate() < 10
            ? "0" + dataDeNascimentoLocal.getDate()
            : dataDeNascimentoLocal.getDate()
        }`
      );
      setUsuario(response);
      console.log(response);
    }
    getUsuario();
  });

  async function submitHandler(event) {
    event.preventDefault();
    const payload = {
      id: idUsuario,
      name: nome,
      email,
      password: senha,
      bio,
      rate,
      dataDeNascimento: dataDeNascimento,
    };
    await axios
      .put(`http://localhost:8080/usuarios/${usuario.id}`, payload)
      .then((response) => {
        if (response.status === 200) {
          router.push("/users/list");
          alert("Usuário atualizado com sucesso!");
        } else {
          router.push("/users/list");
          alert("Erro ao atualizar usuário!");
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Erro ao atualizar usuário!");
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
      menuTitle="Formulário de edição de usuário"
      returnCallback={() => router.push("/users/list")}
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
          textoDoBotao={"Atualizar Usuário"}
          type={"submit"}
          className={" mt-4 "}
        ></Button>
      </form>
    </SubPageContainer>
  );
};

export default UpdateUsuarioPage;
