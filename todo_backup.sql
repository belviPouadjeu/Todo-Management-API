PGDMP      #    
            }            todo_db    17.2    17.2 	    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            �           1262    92370    todo_db    DATABASE     |   CREATE DATABASE todo_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'French_Cameroon.1252';
    DROP DATABASE todo_db;
                     postgres    false            �            1259    92372    todo    TABLE     �   CREATE TABLE public.todo (
    tod_id bigint NOT NULL,
    description character varying(100) NOT NULL,
    status character varying(255) NOT NULL,
    title character varying(30) NOT NULL
);
    DROP TABLE public.todo;
       public         heap r       postgres    false            �            1259    92371    todo_tod_id_seq    SEQUENCE     �   ALTER TABLE public.todo ALTER COLUMN tod_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.todo_tod_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    218            �          0    92372    todo 
   TABLE DATA           B   COPY public.todo (tod_id, description, status, title) FROM stdin;
    public               postgres    false    218   �       �           0    0    todo_tod_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.todo_tod_id_seq', 8, true);
          public               postgres    false    217            "           2606    92376    todo todo_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.todo
    ADD CONSTRAINT todo_pkey PRIMARY KEY (tod_id);
 8   ALTER TABLE ONLY public.todo DROP CONSTRAINT todo_pkey;
       public                 postgres    false    218            �   L  x�]�?O�0���Sxc��wNH7����m}m �C▃O�[��q[�<?��}�n{j����rG�����1F��1R�R�5C��e*�=������!�o%N�1�Ŋ&b��d`�*��W�I������h��~��0��'��6&�%�l�*�*s�v�&�ʭ�ɭ(E���chp��n=�n��1Q�8rC�W��	-*�X(:b�O��X��*�F���b{�I�1�L����F���cٳ��tЅ����kci�a^�����|��L�,̎��pW��;W�h�Cǋ���#fc�lo��`C������͡�����?��F     