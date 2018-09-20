/*
Componentes do Grupo DESCHATEIE

Henry Van Angel  - RM81129
Fernando Ribeiro - RM80577
Marco Antonio Borges Oliveira - RM81207

*/
-- Exclusão das tabelas e respectivos relacionamentos
/*
DROP TABLE T_SCP_AGENDAMENTO CASCADE CONSTRAINTS;
DROP TABLE T_SCP_AVALIACAO  CASCADE CONSTRAINTS;
DROP TABLE T_SCP_CONSULTA  CASCADE CONSTRAINTS;
DROP TABLE T_SCP_CONVERSA  CASCADE CONSTRAINTS;
DROP TABLE T_SCP_ENDERECO  CASCADE CONSTRAINTS;
DROP TABLE T_SCP_FORMA_PAGAMENTO CASCADE CONSTRAINTS;
DROP TABLE T_SCP_PACIENTE  CASCADE CONSTRAINTS;
DROP TABLE T_SCP_PAGAMENTO  CASCADE CONSTRAINTS;
DROP TABLE T_SCP_PSI_ONLINE  CASCADE CONSTRAINTS;
DROP TABLE T_SCP_PSICOLOGO  CASCADE CONSTRAINTS;
DROP TABLE T_SCP_USUARIO CASCADE CONSTRAINTS;
DROP TABLE T_SCP_USUARIO_CONVERSA CASCADE CONSTRAINTS;
DROP TABLE T_SCP_VOLUNTARIO  CASCADE CONSTRAINTS;

*/

-- CRIAÇÃO DA TABELA AGENDAMENTO
CREATE TABLE t_scp_agendamento (
    cd_agendamento   NUMBER(5) NOT NULL,
    cd_usuario       NUMBER(5) NOT NULL,
    cd_psicologo     NUMBER(5) NOT NULL,
    dt_agendamento          DATE NOT NULL
);
-- CONSTRAINTS
ALTER TABLE t_scp_agendamento ADD CONSTRAINT pk_scp_agendamento PRIMARY KEY ( cd_agendamento );

-- CRIAÇÃO DA TABELA AVALIAÇÃO
CREATE TABLE t_scp_avaliacao (
    cd_psicologo   NUMBER(5) NOT NULL,
    cd_avaliacao   NUMBER(4) NOT NULL,
    cd_usuario     NUMBER(5) NOT NULL,
    dt_avaliacao   DATE NOT NULL,
    ds_resultado   VARCHAR2(80) NOT NULL
);
-- CONSTRAINTS
ALTER TABLE t_scp_avaliacao ADD CONSTRAINT pk_scp_avaliacao PRIMARY KEY ( cd_avaliacao,
cd_psicologo );

-- CRIAÇÃO DA TABELA CONSULTA
CREATE TABLE t_scp_consulta (
    cd_consulta            NUMBER(5) NOT NULL,
    cd_psicologo_on        NUMBER(5) NOT NULL,
    cd_paciente            NUMBER(5) NOT NULL,
    cd_conversa            NUMBER(5) NOT NULL,
    dt_consulta            DATE NOT NULL,
    vl_nota_atendimento    NUMBER(1),
    ds_comentario          VARCHAR2(120)
);
-- CONSTRAINTS
ALTER TABLE t_scp_consulta
    ADD CONSTRAINT pk_scp_consulta PRIMARY KEY ( cd_psicologo_on,
    cd_paciente,
    cd_consulta );
    
ALTER TABLE t_scp_consulta
    ADD CONSTRAINT ck_scp_consulta_nota CHECK (
        vl_nota_atendimento >= 1
        AND vl_nota_atendimento <= 5
    );

-- CRIAÇÃO DA TABELA CONVERSA
CREATE TABLE t_scp_conversa (
    cd_conversa     NUMBER(5) NOT NULL,
    hr_inicio       DATE NOT NULL,
    qt_respostas    NUMBER(3) NOT NULL,
    ds_historico    VARCHAR2(2000) NOT NULL,
    hr_termino      DATE,
    cd_voluntario   NUMBER
);
-- CONSTRAINTS
ALTER TABLE t_scp_conversa ADD CONSTRAINT pk_scp_conversa PRIMARY KEY ( cd_conversa );

ALTER TABLE t_scp_conversa ADD CONSTRAINT ck_scp_conv_horario CHECK ( hr_termino >= hr_inicio );

-- CRIAÇÃO DA TABELA ENDEREÇO
CREATE TABLE t_scp_endereco (
    cd_endereco      NUMBER(6) NOT NULL,
    cd_psicologo     NUMBER(5),
    cd_voluntario    NUMBER(5),
    ds_tipo          VARCHAR2(20) NOT NULL,
    ds_logradouro    VARCHAR2(60) NOT NULL,
    nr_numero        VARCHAR2(10) NOT NULL,
    ds_complemento   VARCHAR2(10),
    nr_cep           VARCHAR2(8) NOT NULL,
    ds_bairro        VARCHAR2(30) NOT NULL,
    ds_cidade        VARCHAR2(30) NOT NULL,
    ds_uf            CHAR(2) NOT NULL,
    ds_pais          VARCHAR2(20) NOT NULL
);
-- CONSTRAINTS
ALTER TABLE t_scp_endereco ADD CONSTRAINT pk_scp_endereco PRIMARY KEY ( cd_endereco );

-- CRIAÇÃO DA TABELA FORMA DE PAGAMENTO
CREATE TABLE t_scp_forma_pagamento (
    cd_forma_pgto        NUMBER(2) NOT NULL,
    ds_forma_pgto   VARCHAR2(40) NOT NULL
);
-- CONSTRAINTS
ALTER TABLE t_scp_forma_pagamento ADD CONSTRAINT pk_scp_forma_pagamento PRIMARY KEY ( cd_forma_pgto );

ALTER TABLE t_scp_forma_pagamento ADD CONSTRAINT UN_SCP_FORMA_PGTO_DS UNIQUE ( ds_forma_pgto );

-- CRIAÇÃO DA TABELA PACIENTE
CREATE TABLE t_scp_paciente (
    cd_paciente               NUMBER(5) NOT NULL,
    ds_cep                    VARCHAR2(8) NOT NULL,
    nr_cpf                    NUMBER(11) NOT NULL,
    ds_historico              VARCHAR2(2000),
    nr_consultas_realizadas   NUMBER(3) NOT NULL
);
-- CONSTRAINTS
ALTER TABLE t_scp_paciente ADD CONSTRAINT pk_scp_paciente PRIMARY KEY ( cd_paciente );

ALTER TABLE t_scp_paciente ADD CONSTRAINT un_scp_paciente_cpf UNIQUE ( nr_cpf );

-- CRIAÇÃO DA TABELA PAGAMENTO
CREATE TABLE t_scp_pagamento (
    cd_pagamento      NUMBER(5) NOT NULL,
    cd_psicologo_on   NUMBER(5) NOT NULL,
    cd_paciente       NUMBER(5) NOT NULL,
    cd_consulta       NUMBER(5) NOT NULL,
    cd_forma_pgto     NUMBER(2) NOT NULL,
    vl_consulta       NUMBER(6,2) NOT NULL,
    dt_pagamento      DATE NOT NULL,
    qt_parcelas       NUMBER(2),
    vl_desconto       NUMBER(5,2)
);
-- CONSTRAINTS
ALTER TABLE t_scp_pagamento ADD CONSTRAINT pk_scp_pagamento PRIMARY KEY ( cd_pagamento );

ALTER TABLE t_scp_pagamento
    ADD CONSTRAINT ck_scp_pgto_parcela CHECK (
        qt_parcelas > 0
        AND qt_parcelas <= 12
    );

ALTER TABLE t_scp_pagamento
    ADD CONSTRAINT ck_scp_pgto_desconto CHECK ( vl_desconto < vl_consulta * 0.5 );

-- CRIAÇÃO DA TABELA PSICOLOGO ONLINE
CREATE TABLE t_scp_psi_online (
    cd_psicologo_on        NUMBER(5) NOT NULL,
    ds_periodo             VARCHAR2(40) NOT NULL,
    ds_forma_atendimento   VARCHAR2(40) NOT NULL,
    vl_nota_atendimento    NUMBER(1),
    qt_atendimento         NUMBER(4)
);
-- CONSTRAINTS
ALTER TABLE t_scp_psi_online ADD CONSTRAINT pk_scp_psi_online PRIMARY KEY ( cd_psicologo_on );

ALTER TABLE t_scp_psi_online
    ADD CONSTRAINT ck_scp_psi_on_nota CHECK (
        vl_nota_atendimento >= 1
        AND vl_nota_atendimento <= 5
    );


-- CRIAÇÃO DA TABELA PSICOLOGO
CREATE TABLE t_scp_psicologo (
    cd_psicologo   NUMBER(5) NOT NULL,
    nr_crp         NUMBER(7) NOT NULL,
    ds_formacao    VARCHAR2(40) NOT NULL,
    ds_biografia   VARCHAR2(120),
    ds_telefone    NUMBER(11) NOT NULL,
    vl_consulta    NUMBER(6,2)
);
-- CONSTRAINTS
ALTER TABLE t_scp_psicologo ADD CONSTRAINT pk_scp_psicologo PRIMARY KEY ( cd_psicologo );

ALTER TABLE t_scp_psicologo ADD CONSTRAINT un_scp_psicologo_crp UNIQUE ( nr_crp );

-- CRIAÇÃO DA TABELA USUARIO
CREATE TABLE t_scp_usuario (
    cd_usuario           NUMBER(5) NOT NULL,
    nm_usuario           VARCHAR2(80) NOT NULL,
    ds_email             VARCHAR2(60) NOT NULL,
    dt_nascimento        DATE NOT NULL,
    ds_login             VARCHAR2(20) NOT NULL,
    ds_senha             VARCHAR2(15) NOT NULL,
    nr_nivel_permissao   NUMBER(2) NOT NULL,
    ds_foto              VARCHAR2(120),
    ds_genero            VARCHAR2(20)
);
-- CONSTRAINTS
ALTER TABLE t_scp_usuario ADD CONSTRAINT pk_scp_usuario PRIMARY KEY ( cd_usuario );

ALTER TABLE t_scp_usuario ADD CONSTRAINT un_scp_usuario_email UNIQUE ( ds_email );

ALTER TABLE t_scp_usuario ADD CONSTRAINT un_scp_usuario_login UNIQUE ( ds_login );
-- CRIAÇÃO DA TABELA USUARIO CONVERSA
CREATE TABLE t_scp_usuario_conversa (
    cd_usuario    NUMBER(5) NOT NULL,
    cd_conversa   NUMBER(5) NOT NULL
);
-- CONSTRAINTS
ALTER TABLE t_scp_usuario_conversa ADD CONSTRAINT pk_scp_usuario_conversa PRIMARY KEY ( cd_usuario,
cd_conversa );

-- CRIAÇÃO DA TABELA VOLUNTARIO
CREATE TABLE t_scp_voluntario (
    cd_voluntario   NUMBER(5) NOT NULL,
    nr_rg           VARCHAR2(9) NOT NULL,
    nr_cpf          NUMBER(11) NOT NULL,
    ds_formacao     VARCHAR2(60) NOT NULL,
    ds_periodo      VARCHAR2(40) NOT NULL,
    ds_comentario   VARCHAR2(80) NOT NULL,
    ds_telefone     NUMBER(11) NOT NULL
);
-- CONSTRAINTS
ALTER TABLE t_scp_voluntario ADD CONSTRAINT pk_scp_voluntario PRIMARY KEY ( cd_voluntario );

ALTER TABLE t_scp_voluntario ADD CONSTRAINT un_scp_voluntario_cpf UNIQUE ( nr_cpf );

ALTER TABLE t_scp_voluntario ADD CONSTRAINT un_scp_voluntario_rg UNIQUE ( nr_rg );

-- CHAVES ESTRANGEIRAS--
-- RELACIONAMENTO ENTRE AGENDAMENTO E PSICOLOGO--
ALTER TABLE t_scp_agendamento
    ADD CONSTRAINT fk_scp_agenda_psi FOREIGN KEY ( cd_psicologo )
        REFERENCES t_scp_psicologo ( cd_psicologo );
-- RELACIONAMENTO ENTRE AGENDAMENTO E USUARIO--
ALTER TABLE t_scp_agendamento
    ADD CONSTRAINT fk_scp_agenda_usuario FOREIGN KEY ( cd_usuario )
        REFERENCES t_scp_usuario ( cd_usuario );
-- RELACIONAMENTO ENTRE AVALIACAO E PSICOLOGO--
ALTER TABLE t_scp_avaliacao
    ADD CONSTRAINT fk_scp_aval_psi FOREIGN KEY ( cd_psicologo )
        REFERENCES t_scp_psicologo ( cd_psicologo );
-- RELACIONAMENTO ENTRE AVALIACAO E USUARIO--
ALTER TABLE t_scp_avaliacao
    ADD CONSTRAINT fk_scp_aval_usuario FOREIGN KEY ( cd_usuario )
        REFERENCES t_scp_usuario ( cd_usuario );
-- RELACIONAMENTO ENTRE CONSULTA E CONVERSA--
ALTER TABLE t_scp_consulta
    ADD CONSTRAINT fk_scp_cons_conversa FOREIGN KEY ( cd_conversa )
        REFERENCES t_scp_conversa ( cd_conversa );
-- RELACIONAMENTO ENTRE CONSULTA E PACIENTE--
ALTER TABLE t_scp_consulta
    ADD CONSTRAINT fk_scp_cons_paciente FOREIGN KEY ( cd_paciente )
        REFERENCES t_scp_paciente ( cd_paciente );
-- RELACIONAMENTO ENTRE CONSULTA E PSICOLOGO ONLINE--
ALTER TABLE t_scp_consulta
    ADD CONSTRAINT fk_scp_cons_psi_online FOREIGN KEY ( cd_psicologo_on )
        REFERENCES t_scp_psi_online ( cd_psicologo_on );
-- RELACIONAMENTO ENTRE ENDEREÇO E PSICOLOGO--
ALTER TABLE t_scp_endereco
    ADD CONSTRAINT fk_scp_endco_psi FOREIGN KEY ( cd_psicologo )
        REFERENCES t_scp_psicologo ( cd_psicologo );
-- RELACIONAMENTO ENTRE ENDEREÇO E VOLUNTARIO--
ALTER TABLE t_scp_endereco
    ADD CONSTRAINT fk_scp_endco_vol FOREIGN KEY ( cd_voluntario )
        REFERENCES t_scp_voluntario ( cd_voluntario );
-- RELACIONAMENTO ENTRE PACIENTE E USUARIO--
ALTER TABLE t_scp_paciente
    ADD CONSTRAINT fk_scp_paciente_usuario FOREIGN KEY ( cd_paciente )
        REFERENCES t_scp_usuario ( cd_usuario );
-- RELACIONAMENTO ENTRE PAGAMENTO E CONSULTA--
ALTER TABLE t_scp_pagamento
    ADD CONSTRAINT fk_scp_pgto_consulta FOREIGN KEY ( cd_psicologo_on,
    cd_paciente,
    cd_consulta )
        REFERENCES t_scp_consulta ( cd_psicologo_on,
        cd_paciente,
        cd_consulta );
-- RELACIONAMENTO ENTRE PAGAMENTO E FORMA DE PAGAMENTO--
ALTER TABLE t_scp_pagamento
    ADD CONSTRAINT fk_scp_pgto_forma_pgto FOREIGN KEY ( cd_forma_pgto )
        REFERENCES t_scp_forma_pagamento ( cd_forma_pgto );
-- RELACIONAMENTO ENTRE PSICOLOGO ONLINE E PSICOLOGO--
ALTER TABLE t_scp_psi_online
    ADD CONSTRAINT fk_scp_psi_on_psi FOREIGN KEY ( cd_psicologo_on )
        REFERENCES t_scp_psicologo ( cd_psicologo );
-- RELACIONAMENTO ENTRE PSICOLOGO E USUARIO--
ALTER TABLE t_scp_psicologo
    ADD CONSTRAINT fk_scp_psi_usuario FOREIGN KEY ( cd_psicologo )
        REFERENCES t_scp_usuario ( cd_usuario );
-- RELACIONAMENTO ENTRE USUARIO CONVERSA E CONVERSA--
ALTER TABLE t_scp_usuario_conversa
    ADD CONSTRAINT fk_scp_usuario_conv_conv FOREIGN KEY ( cd_conversa )
        REFERENCES t_scp_conversa ( cd_conversa );
-- RELACIONAMENTO ENTRE USUARIO CONVERSA E USUARIO--
ALTER TABLE t_scp_usuario_conversa
    ADD CONSTRAINT fk_scp_usuario_conv_usuario FOREIGN KEY ( cd_usuario )
        REFERENCES t_scp_usuario ( cd_usuario );
-- RELACIONAMENTO ENTRE VOLUNTARIO E USUARIO--
ALTER TABLE t_scp_voluntario
    ADD CONSTRAINT fk_scp_vol_usuario FOREIGN KEY ( cd_voluntario )
        REFERENCES t_scp_usuario ( cd_usuario );