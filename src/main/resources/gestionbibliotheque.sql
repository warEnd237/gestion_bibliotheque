--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: gestionbibliotheque; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE gestionbibliotheque WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'French_Cameroon.1252';


ALTER DATABASE gestionbibliotheque OWNER TO postgres;

\connect gestionbibliotheque

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: emprunt; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.emprunt (
    idemprunt integer NOT NULL,
    membreid integer,
    livreid integer,
    dateemprunt date NOT NULL,
    dateretourprevue date NOT NULL,
    dateretoureffective date,
    penalite numeric(10,2) DEFAULT 0
);


ALTER TABLE public.emprunt OWNER TO postgres;

--
-- Name: emprunt_idemprunt_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.emprunt_idemprunt_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.emprunt_idemprunt_seq OWNER TO postgres;

--
-- Name: emprunt_idemprunt_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.emprunt_idemprunt_seq OWNED BY public.emprunt.idemprunt;


--
-- Name: livre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.livre (
    id integer NOT NULL,
    titre character varying(100) NOT NULL,
    auteur character varying(100) NOT NULL,
    categorie character varying(50),
    nombreexemplaires integer NOT NULL
);


ALTER TABLE public.livre OWNER TO postgres;

--
-- Name: livre_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.livre_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.livre_id_seq OWNER TO postgres;

--
-- Name: livre_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.livre_id_seq OWNED BY public.livre.id;


--
-- Name: membre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.membre (
    id integer NOT NULL,
    nom character varying(100) NOT NULL,
    prenom character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    adhesiondate date NOT NULL
);


ALTER TABLE public.membre OWNER TO postgres;

--
-- Name: membre_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.membre_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.membre_id_seq OWNER TO postgres;

--
-- Name: membre_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.membre_id_seq OWNED BY public.membre.id;


--
-- Name: emprunt idemprunt; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunt ALTER COLUMN idemprunt SET DEFAULT nextval('public.emprunt_idemprunt_seq'::regclass);


--
-- Name: livre id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livre ALTER COLUMN id SET DEFAULT nextval('public.livre_id_seq'::regclass);


--
-- Name: membre id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.membre ALTER COLUMN id SET DEFAULT nextval('public.membre_id_seq'::regclass);


--
-- Data for Name: emprunt; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.emprunt (idemprunt, membreid, livreid, dateemprunt, dateretourprevue, dateretoureffective, penalite) FROM stdin;
\.


--
-- Data for Name: livre; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.livre (id, titre, auteur, categorie, nombreexemplaires) FROM stdin;
1	Les 48 Lois du Pouvoir	Robert Greene	Motivation personnelle	5
2	Naruto, Tome 1	Masashi Kishimoto	Manga	10
3	Une Histoire de l'Afrique	John Iliffe	Histoire	8
4	Ast‚rix le Gaulois	Ren‚ Goscinny et Albert Uderzo	BD	7
5	Le Petit Prince	Antoine de Saint-Exup‚ry	Philosophie	12
6	One Piece, Tome 1	Eiichiro Oda	Manga	15
7	Sapiens : Une brŠve histoire de l'humanit‚	Yuval Noah Harari	Histoire	6
8	L'Alchimiste	Paulo Coelho	Motivation personnelle	9
9	Les Mis‚rables	Victor Hugo	Classique	4
10	Death Note, Tome 1	Tsugumi Ohba et Takeshi Obata	Manga	11
11	Tintin au Congo	Herg‚	BD	5
12	Les M‚ditations M‚taphysiques	Ren‚ Descartes	Philosophie	3
13	Rich Dad Poor Dad	Robert T. Kiyosaki	Motivation personnelle	7
14	L'Afrique et ses d‚fis	Felwine Sarr	Histoire	8
15	Dragon Ball, Tome 1	Akira Toriyama	Manga	13
\.


--
-- Data for Name: membre; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.membre (id, nom, prenom, email, adhesiondate) FROM stdin;
1	Kamgaing Bobda	Joseph Warren	lebobi35@gmail.com	2025-01-16
\.


--
-- Name: emprunt_idemprunt_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.emprunt_idemprunt_seq', 1, false);


--
-- Name: livre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.livre_id_seq', 15, true);


--
-- Name: membre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.membre_id_seq', 1, true);


--
-- Name: emprunt emprunt_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT emprunt_pkey PRIMARY KEY (idemprunt);


--
-- Name: livre livre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livre
    ADD CONSTRAINT livre_pkey PRIMARY KEY (id);


--
-- Name: membre membre_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.membre
    ADD CONSTRAINT membre_email_key UNIQUE (email);


--
-- Name: membre membre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.membre
    ADD CONSTRAINT membre_pkey PRIMARY KEY (id);


--
-- Name: emprunt emprunt_livreid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT emprunt_livreid_fkey FOREIGN KEY (livreid) REFERENCES public.livre(id);


--
-- Name: emprunt emprunt_membreid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT emprunt_membreid_fkey FOREIGN KEY (membreid) REFERENCES public.membre(id);


--
-- PostgreSQL database dump complete
--

