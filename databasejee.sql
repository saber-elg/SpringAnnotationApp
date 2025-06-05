-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : sam. 30 mai 2025 à 20:17
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `databasejee`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

CREATE TABLE `administrateur` (
  `utilisateur_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `administrateur`
--

INSERT INTO `administrateur` (`utilisateur_id`) VALUES
(30);

-- --------------------------------------------------------

--
-- Structure de la table `annotateur`
--

CREATE TABLE `annotateur` (
  `utilisateur_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `annotateur`
--

INSERT INTO `annotateur` (`utilisateur_id`) VALUES
(42),
(43),
(44),
(45),
(48);

-- --------------------------------------------------------

--
-- Structure de la table `annotation`
--

CREATE TABLE `annotation` (
  `Id` int NOT NULL,
  `classe_choisie_Id` int DEFAULT NULL,
  `couple_texte_Id` int DEFAULT NULL,
  `annotateur_Id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `annotation`
--

INSERT INTO `annotation` (`Id`, `classe_choisie_Id`, `couple_texte_Id`, `annotateur_Id`) VALUES
(53, 65, 3721, 43),
(54, 66, 3722, 43),
(55, 65, 3723, 43),
(56, 65, 3724, 43),
(57, 66, 3725, 43),
(58, 67, 3726, 44),
(59, 68, 3732, 44);

-- --------------------------------------------------------

--
-- Structure de la table `classe_possible`
--

CREATE TABLE `classe_possible` (
  `Id` int NOT NULL,
  `intitule_Classe` varchar(100) DEFAULT NULL,
  `dataset_Id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `classe_possible`
--

INSERT INTO `classe_possible` (`Id`, `intitule_Classe`, `dataset_Id`) VALUES
(65, 'A', 30),
(66, 'B', 30),
(67, 'simailires', 31),
(68, 'differents', 31);

-- --------------------------------------------------------

--
-- Structure de la table `couple_texte`
--

CREATE TABLE `couple_texte` (
  `Id` int NOT NULL,
  `texte1` text DEFAULT NULL,
  `texte2` text DEFAULT NULL,
  `dataset_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `couple_texte`
--

INSERT INTO `couple_texte` (`Id`, `texte1`, `texte2`, `dataset_id`) VALUES
(3721, 'Le volcan est entré en éruption.', 'La lave s\'est mise à couler.', 30),
(3722, 'Le patient a reçu un diagnostic de grippe.', 'Il est atteint d\'une maladie virale.', 30),
(3723, 'La voiture ne démarre plus.', 'Elle fonctionne parfaitement.', 30),
(3724, 'Les enfants jouent dans le parc.', 'Des gamins s\'amusent dehors.', 30),
(3725, 'Le train est en retard.', 'Le train est arrivé à l\'heure.', 30),
(3726, 'L’enfant mange une pomme.', 'L’enfant a faim.', 31),
(3727, 'Le chat dort sur le canapé.', 'L’animal se repose sur le sofa.', 31),
(3728, 'Ils voyagent en train.', 'Ils prennent le train.', 31),
(3729, 'Marie a acheté une voiture.', 'Marie n’a rien acheté.', 31),
(3730, 'Jean lit un livre.', 'Jean regarde un film.', 31),
(3731, 'La porte est ouverte.', 'La porte est fermée.', 31),
(3732, 'Marie a acheté une voiture.', 'Marie n’a rien acheté.', 31),
(3733, 'Ils voyagent en train.', 'Ils prennent le train.', 31),
(3734, 'Le téléphone sonne.', 'Personne n\'appelle.', 31),
(3735, 'Le ciel est bleu.', 'Le ciel est clair.', 31),
(3736, 'Le bébé dort paisiblement.', 'Le bébé pleure.', 31),
(3737, 'La porte est ouverte.', 'La porte est fermée.', 31),
(3738, 'L’enfant mange une pomme.', 'L’enfant a faim.', 31),
(3739, 'L’étudiant révise pour l’examen.', 'L’étudiant ne se prépare pas.', 31),
(3740, 'Elle travaille dans une banque.', 'Elle est employée dans le secteur financier.', 31),
(3741, 'Il pleut dehors.', 'Le soleil brille.', 31),
(3742, 'La neige tombe en hiver.', 'Il fait chaud en hiver.', 31),
(3743, 'Paul joue au foot.', 'Paul pratique un sport.', 31),
(3744, 'Le repas est servi.', 'Ils attendent encore leur plat.', 31),
(3745, 'Le feu est rouge.', 'Les voitures s’arrêtent.', 31),
(3746, 'La voiture est rouge.', 'Le véhicule a une couleur vive.', 31),
(3747, 'Le chien aboie fort.', 'Le chien est silencieux.', 31),
(3748, 'Le bébé dort paisiblement.', 'Le bébé pleure.', 31),
(3749, 'Le feu est rouge.', 'Les voitures s’arrêtent.', 31),
(3750, 'L’étudiant révise pour l’examen.', 'L’étudiant ne se prépare pas.', 31),
(3751, 'L’enfant mange une pomme.', 'L’enfant a faim.', 31),
(3752, 'La neige tombe en hiver.', 'Il fait chaud en hiver.', 31),
(3753, 'La neige tombe en hiver.', 'Il fait chaud en hiver.', 31),
(3754, 'Le repas est servi.', 'Ils attendent encore leur plat.', 31),
(3755, 'Le feu est rouge.', 'Les voitures s’arrêtent.', 31),
(3756, 'L’enfant mange une pomme.', 'L’enfant a faim.', 31),
(3757, 'Elle travaille dans une banque.', 'Elle est employée dans le secteur financier.', 31),
(3758, 'La voiture est rouge.', 'Le véhicule a une couleur vive.', 31),
(3759, 'Le repas est servi.', 'Ils attendent encore leur plat.', 31),
(3760, 'La voiture est rouge.', 'Le véhicule a une couleur vive.', 31),
(3761, 'La neige tombe en hiver.', 'Il fait chaud en hiver.', 31),
(3762, 'Le feu est rouge.', 'Les voitures s’arrêtent.', 31),
(3763, 'La voiture est rouge.', 'Le véhicule a une couleur vive.', 31),
(3764, 'Elle travaille dans une banque.', 'Elle est employée dans le secteur financier.', 31),
(3765, 'Le feu est rouge.', 'Les voitures s’arrêtent.', 31),
(3766, 'Les oiseaux chantent le matin.', 'Le matin est silencieux.', 31),
(3767, 'Il pleut dehors.', 'Le soleil brille.', 31),
(3768, 'La neige tombe en hiver.', 'Il fait chaud en hiver.', 31),
(3769, 'Il pleut dehors.', 'Le soleil brille.', 31),
(3770, 'La voiture est rouge.', 'Le véhicule a une couleur vive.', 31),
(3771, 'Le repas est servi.', 'Ils attendent encore leur plat.', 31),
(3772, 'L’enfant mange une pomme.', 'L’enfant a faim.', 31),
(3773, 'L’étudiant révise pour l’examen.', 'L’étudiant ne se prépare pas.', 31),
(3774, 'Le ciel est bleu.', 'Le ciel est clair.', 31),
(3775, 'Le feu est rouge.', 'Les voitures s’arrêtent.', 31),
(3776, 'Le ciel est bleu.', 'Le ciel est clair.', 31),
(3777, 'La neige tombe en hiver.', 'Il fait chaud en hiver.', 31),
(3778, 'La neige tombe en hiver.', 'Il fait chaud en hiver.', 31),
(3779, 'La porte est ouverte.', 'La porte est fermée.', 31),
(3780, 'Le ciel est bleu.', 'Le ciel est clair.', 31),
(3781, 'La neige tombe en hiver.', 'Il fait chaud en hiver.', 31),
(3782, 'Elle travaille dans une banque.', 'Elle est employée dans le secteur financier.', 31),
(3783, 'La voiture est rouge.', 'Le véhicule a une couleur vive.', 31),
(3784, 'Le ciel est bleu.', 'Le ciel est clair.', 31),
(3785, 'Le chat dort sur le canapé.', 'L’animal se repose sur le sofa.', 31),
(3786, 'Le ciel est bleu.', 'Le ciel est clair.', 31),
(3787, 'Le téléphone sonne.', 'Personne n\'appelle.', 31),
(3788, 'La voiture est rouge.', 'Le véhicule a une couleur vive.', 31),
(3789, 'La porte est ouverte.', 'La porte est fermée.', 31),
(3790, 'Le feu est rouge.', 'Les voitures s’arrêtent.', 31),
(3791, 'La neige tombe en hiver.', 'Il fait chaud en hiver.', 31),
(3792, 'Jean lit un livre.', 'Jean regarde un film.', 31),
(3793, 'Paul joue au foot.', 'Paul pratique un sport.', 31),
(3794, 'Le repas est servi.', 'Ils attendent encore leur plat.', 31),
(3795, 'Les oiseaux chantent le matin.', 'Le matin est silencieux.', 31),
(3796, 'Les fleurs sont fanées.', 'Les fleurs sont fraîches.', 31),
(3797, 'Le téléphone sonne.', 'Personne n\'appelle.', 31),
(3798, 'La voiture est rouge.', 'Le véhicule a une couleur vive.', 31),
(3799, 'Le feu est rouge.', 'Les voitures s’arrêtent.', 31),
(3800, 'L’enfant mange une pomme.', 'L’enfant a faim.', 31),
(3801, 'Les oiseaux chantent le matin.', 'Le matin est silencieux.', 31),
(3802, 'La voiture est rouge.', 'Le véhicule a une couleur vive.', 31),
(3803, 'Le ciel est bleu.', 'Le ciel est clair.', 31),
(3804, 'Ils voyagent en train.', 'Ils prennent le train.', 31),
(3805, 'L’enfant mange une pomme.', 'L’enfant a faim.', 31),
(3806, 'Le téléphone sonne.', 'Personne n\'appelle.', 31),
(3807, 'L’enfant mange une pomme.', 'L’enfant a faim.', 31),
(3808, 'Il pleut dehors.', 'Le soleil brille.', 31),
(3809, 'Marie a acheté une voiture.', 'Marie n’a rien acheté.', 31),
(3810, 'Elle travaille dans une banque.', 'Elle est employée dans le secteur financier.', 31),
(3811, 'Le repas est servi.', 'Ils attendent encore leur plat.', 31),
(3812, 'Ils voyagent en train.', 'Ils prennent le train.', 31),
(3813, 'Paul joue au foot.', 'Paul pratique un sport.', 31),
(3814, 'Le ciel est bleu.', 'Le ciel est clair.', 31),
(3815, 'Il pleut dehors.', 'Le soleil brille.', 31),
(3816, 'Ils voyagent en train.', 'Ils prennent le train.', 31),
(3817, 'Le feu est rouge.', 'Les voitures s’arrêtent.', 31),
(3818, 'La neige tombe en hiver.', 'Il fait chaud en hiver.', 31),
(3819, 'Il pleut dehors.', 'Le soleil brille.', 31),
(3820, 'La voiture est rouge.', 'Le véhicule a une couleur vive.', 31),
(3821, 'Le chien aboie fort.', 'Le chien est silencieux.', 31),
(3822, 'Le chien aboie fort.', 'Le chien est silencieux.', 31),
(3823, 'L’enfant mange une pomme.', 'L’enfant a faim.', 31),
(3824, 'Marie a acheté une voiture.', 'Marie n’a rien acheté.', 31),
(3825, 'Le feu est rouge.', 'Les voitures s’arrêtent.', 31),
(3826, 'Ils voyagent en train.', 'Ils prennent le train.', 31),
(3827, 'Le feu est rouge.', 'Les voitures s’arrêtent.', 31),
(3828, 'Marie a acheté une voiture.', 'Marie n’a rien acheté.', 31),
(3829, 'La voiture est rouge.', 'Le véhicule a une couleur vive.', 31),
(3830, 'Le ciel est bleu.', 'Le ciel est clair.', 31),
(3831, 'Il pleut dehors.', 'Le soleil brille.', 31),
(3832, 'Le chien aboie fort.', 'Le chien est silencieux.', 31),
(3833, 'La neige tombe en hiver.', 'Il fait chaud en hiver.', 31),
(3834, 'Le téléphone sonne.', 'Personne n\'appelle.', 31),
(3835, 'Le feu est rouge.', 'Les voitures s’arrêtent.', 31),
(3836, 'Le chien aboie fort.', 'Le chien est silencieux.', 31),
(3837, 'Le ciel est bleu.', 'Le ciel est clair.', 31),
(3838, 'Marie a acheté une voiture.', 'Marie n’a rien acheté.', 31),
(3839, 'L’étudiant révise pour l’examen.', 'L’étudiant ne se prépare pas.', 31),
(3840, 'Les oiseaux chantent le matin.', 'Le matin est silencieux.', 31),
(3841, 'Jean lit un livre.', 'Jean regarde un film.', 31),
(3842, 'L’enfant mange une pomme.', 'L’enfant a faim.', 31),
(3843, 'Il pleut dehors.', 'Le soleil brille.', 31),
(3844, 'Le ciel est bleu.', 'Le ciel est clair.', 31),
(3845, 'Le chat dort sur le canapé.', 'L’animal se repose sur le sofa.', 31),
(3846, 'Les oiseaux chantent le matin.', 'Le matin est silencieux.', 31),
(3847, 'La neige tombe en hiver.', 'Il fait chaud en hiver.', 31),
(3848, 'Elle travaille dans une banque.', 'Elle est employée dans le secteur financier.', 31),
(3849, 'Le téléphone sonne.', 'Personne n\'appelle.', 31),
(3850, 'Le chat dort sur le canapé.', 'L’animal se repose sur le sofa.', 31),
(3851, 'Il pleut dehors.', 'Le soleil brille.', 31),
(3852, 'Le téléphone sonne.', 'Personne n\'appelle.', 31),
(3853, 'Le repas est servi.', 'Ils attendent encore leur plat.', 31),
(3854, 'Marie a acheté une voiture.', 'Marie n’a rien acheté.', 31),
(3855, 'Jean lit un livre.', 'Jean regarde un film.', 31),
(3856, 'L’étudiant révise pour l’examen.', 'L’étudiant ne se prépare pas.', 31),
(3857, 'La neige tombe en hiver.', 'Il fait chaud en hiver.', 31),
(3858, 'Ils voyagent en train.', 'Ils prennent le train.', 31),
(3859, 'Le repas est servi.', 'Ils attendent encore leur plat.', 31),
(3860, 'Le chat dort sur le canapé.', 'L’animal se repose sur le sofa.', 31),
(3861, 'Le téléphone sonne.', 'Personne n\'appelle.', 31),
(3862, 'Les fleurs sont fanées.', 'Les fleurs sont fraîches.', 31),
(3863, 'L’étudiant révise pour l’examen.', 'L’étudiant ne se prépare pas.', 31),
(3864, 'Le feu est rouge.', 'Les voitures s’arrêtent.', 31),
(3865, 'Les oiseaux chantent le matin.', 'Le matin est silencieux.', 31),
(3866, 'Les fleurs sont fanées.', 'Les fleurs sont fraîches.', 31),
(3867, 'Le ciel est bleu.', 'Le ciel est clair.', 31),
(3868, 'L’étudiant révise pour l’examen.', 'L’étudiant ne se prépare pas.', 31),
(3869, 'Le bébé dort paisiblement.', 'Le bébé pleure.', 31),
(3870, 'Le repas est servi.', 'Ils attendent encore leur plat.', 31),
(3871, 'Paul joue au foot.', 'Paul pratique un sport.', 31),
(3872, 'Le téléphone sonne.', 'Personne n\'appelle.', 31),
(3873, 'Paul joue au foot.', 'Paul pratique un sport.', 31),
(3874, 'Elle travaille dans une banque.', 'Elle est employée dans le secteur financier.', 31),
(3875, 'Les oiseaux chantent le matin.', 'Le matin est silencieux.', 31),
(3876, 'Il pleut dehors.', 'Le soleil brille.', 31),
(3877, 'Le téléphone sonne.', 'Personne n\'appelle.', 31),
(3878, 'Le chien aboie fort.', 'Le chien est silencieux.', 31),
(3879, 'La porte est ouverte.', 'La porte est fermée.', 31),
(3880, 'Les oiseaux chantent le matin.', 'Le matin est silencieux.', 31),
(3881, 'Le chat dort sur le canapé.', 'L’animal se repose sur le sofa.', 31),
(3882, 'Le chien aboie fort.', 'Le chien est silencieux.', 31),
(3883, 'Les oiseaux chantent le matin.', 'Le matin est silencieux.', 31),
(3884, 'Paul joue au foot.', 'Paul pratique un sport.', 31),
(3885, 'Le chien aboie fort.', 'Le chien est silencieux.', 31),
(3886, 'La neige tombe en hiver.', 'Il fait chaud en hiver.', 31),
(3887, 'Marie a acheté une voiture.', 'Marie n’a rien acheté.', 31),
(3888, 'La porte est ouverte.', 'La porte est fermée.', 31),
(3889, 'La porte est ouverte.', 'La porte est fermée.', 31),
(3890, 'Ils voyagent en train.', 'Ils prennent le train.', 31),
(3891, 'Elle travaille dans une banque.', 'Elle est employée dans le secteur financier.', 31),
(3892, 'Les oiseaux chantent le matin.', 'Le matin est silencieux.', 31),
(3893, 'Ils voyagent en train.', 'Ils prennent le train.', 31),
(3894, 'Le téléphone sonne.', 'Personne n\'appelle.', 31),
(3895, 'Le bébé dort paisiblement.', 'Le bébé pleure.', 31),
(3896, 'Jean lit un livre.', 'Jean regarde un film.', 31),
(3897, 'Le chien aboie fort.', 'Le chien est silencieux.', 31),
(3898, 'Il pleut dehors.', 'Le soleil brille.', 31),
(3899, 'L’étudiant révise pour l’examen.', 'L’étudiant ne se prépare pas.', 31),
(3900, 'La voiture est rouge.', 'Le véhicule a une couleur vive.', 31),
(3901, 'Jean lit un livre.', 'Jean regarde un film.', 31),
(3902, 'Marie a acheté une voiture.', 'Marie n’a rien acheté.', 31),
(3903, 'La porte est ouverte.', 'La porte est fermée.', 31),
(3904, 'La porte est ouverte.', 'La porte est fermée.', 31),
(3905, 'Paul joue au foot.', 'Paul pratique un sport.', 31),
(3906, 'Le téléphone sonne.', 'Personne n\'appelle.', 31),
(3907, 'Le ciel est bleu.', 'Le ciel est clair.', 31),
(3908, 'Le feu est rouge.', 'Les voitures s’arrêtent.', 31),
(3909, 'Il pleut dehors.', 'Le soleil brille.', 31),
(3910, 'Elle travaille dans une banque.', 'Elle est employée dans le secteur financier.', 31),
(3911, 'La porte est ouverte.', 'La porte est fermée.', 31),
(3912, 'Il pleut dehors.', 'Le soleil brille.', 31),
(3913, 'Le bébé dort paisiblement.', 'Le bébé pleure.', 31),
(3914, 'Le chien aboie fort.', 'Le chien est silencieux.', 31),
(3915, 'Le ciel est bleu.', 'Le ciel est clair.', 31),
(3916, 'Les oiseaux chantent le matin.', 'Le matin est silencieux.', 31),
(3917, 'L’étudiant révise pour l’examen.', 'L’étudiant ne se prépare pas.', 31),
(3918, 'L’enfant mange une pomme.', 'L’enfant a faim.', 31),
(3919, 'Le feu est rouge.', 'Les voitures s’arrêtent.', 31),
(3920, 'Le chat dort sur le canapé.', 'L’animal se repose sur le sofa.', 31),
(3921, 'L’étudiant révise pour l’examen.', 'L’étudiant ne se prépare pas.', 31),
(3922, 'Le ciel est bleu.', 'Le ciel est clair.', 31),
(3923, 'Le feu est rouge.', 'Les voitures s’arrêtent.', 31),
(3924, 'L’étudiant révise pour l’examen.', 'L’étudiant ne se prépare pas.', 31),
(3925, 'Ils voyagent en train.', 'Ils prennent le train.', 31);

-- --------------------------------------------------------

--
-- Structure de la table `dataset`
--

CREATE TABLE `dataset` (
  `Id` int NOT NULL,
  `nom_dataset` varchar(100) DEFAULT NULL,
  `description` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `dataset`
--

INSERT INTO `dataset` (`Id`, `nom_dataset`, `description`) VALUES
(30, 'test', 'test'),
(31, 'similaritee', 'tester');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `Id` int NOT NULL,
  `nom_Role` enum('USER_ROLE','ADMIN_ROLE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`Id`, `nom_Role`) VALUES
(1, 'ADMIN_ROLE'),
(2, 'USER_ROLE');

-- --------------------------------------------------------

--
-- Structure de la table `tache`
--

CREATE TABLE `tache` (
  `Id` int NOT NULL,
  `delai_limite` datetime(6) NOT NULL,
  `dataset_Id` int DEFAULT NULL,
  `annotateur_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `tache`
--

INSERT INTO `tache` (`Id`, `delai_limite`, `dataset_Id`, `annotateur_id`) VALUES
(50, '2025-06-06 00:00:00.000000', 30, NULL),
(51, '2025-06-07 00:00:00.000000', 31, NULL),
(52, '2025-07-13 00:00:00.000000', 31, 43),
(53, '2025-07-12 00:00:00.000000', 31, 43);

-- --------------------------------------------------------

--
-- Structure de la table `tache_couple_texte`
--

CREATE TABLE `tache_couple_texte` (
  `tache_id` int NOT NULL,
  `couple_texte_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `tache_couple_texte`
--

INSERT INTO `tache_couple_texte` (`tache_id`, `couple_texte_id`) VALUES
(50, 3721),
(50, 3722),
(50, 3723),
(50, 3724),
(50, 3725),
(51, 3726),
(51, 3732),
(52, 3727),
(52, 3728),
(52, 3729),
(52, 3730),
(52, 3731),
(52, 3737),
(52, 3738),
(52, 3740),
(52, 3741),
(52, 3742),
(52, 3747),
(52, 3750),
(52, 3751),
(52, 3754),
(52, 3757),
(52, 3759),
(52, 3760),
(52, 3761),
(52, 3762),
(52, 3763),
(52, 3765),
(52, 3766),
(52, 3767),
(52, 3768),
(52, 3769),
(52, 3770),
(52, 3771),
(52, 3772),
(52, 3773),
(52, 3776),
(52, 3777),
(52, 3778),
(52, 3779),
(52, 3781),
(52, 3784),
(52, 3785),
(52, 3787),
(52, 3788),
(52, 3789),
(52, 3793),
(52, 3794),
(52, 3800),
(52, 3803),
(52, 3805),
(52, 3806),
(52, 3807),
(52, 3808),
(52, 3809),
(52, 3811),
(52, 3812),
(52, 3813),
(52, 3814),
(52, 3815),
(52, 3818),
(52, 3819),
(52, 3820),
(52, 3821),
(52, 3826),
(52, 3829),
(52, 3830),
(52, 3833),
(52, 3835),
(52, 3836),
(52, 3837),
(52, 3838),
(52, 3842),
(52, 3844),
(52, 3847),
(52, 3849),
(52, 3851),
(52, 3852),
(52, 3853),
(52, 3854),
(52, 3859),
(52, 3860),
(52, 3862),
(52, 3863),
(52, 3865),
(52, 3867),
(52, 3870),
(52, 3871),
(52, 3872),
(52, 3875),
(52, 3876),
(52, 3877),
(52, 3878),
(52, 3879),
(52, 3883),
(52, 3891),
(52, 3892),
(52, 3893),
(52, 3896),
(52, 3898),
(52, 3905),
(52, 3913),
(52, 3916),
(52, 3919),
(52, 3920),
(52, 3923),
(52, 3924),
(53, 3733),
(53, 3734),
(53, 3735),
(53, 3736),
(53, 3739),
(53, 3743),
(53, 3744),
(53, 3745),
(53, 3746),
(53, 3748),
(53, 3749),
(53, 3752),
(53, 3753),
(53, 3755),
(53, 3756),
(53, 3758),
(53, 3764),
(53, 3774),
(53, 3775),
(53, 3780),
(53, 3782),
(53, 3783),
(53, 3786),
(53, 3790),
(53, 3791),
(53, 3792),
(53, 3795),
(53, 3796),
(53, 3797),
(53, 3798),
(53, 3799),
(53, 3801),
(53, 3802),
(53, 3804),
(53, 3810),
(53, 3816),
(53, 3817),
(53, 3822),
(53, 3823),
(53, 3824),
(53, 3825),
(53, 3827),
(53, 3828),
(53, 3831),
(53, 3832),
(53, 3834),
(53, 3839),
(53, 3840),
(53, 3841),
(53, 3843),
(53, 3845),
(53, 3846),
(53, 3848),
(53, 3850),
(53, 3855),
(53, 3856),
(53, 3857),
(53, 3858),
(53, 3861),
(53, 3864),
(53, 3866),
(53, 3868),
(53, 3869),
(53, 3873),
(53, 3874),
(53, 3880),
(53, 3881),
(53, 3882),
(53, 3884),
(53, 3885),
(53, 3886),
(53, 3887),
(53, 3888),
(53, 3889),
(53, 3890),
(53, 3894),
(53, 3895),
(53, 3897),
(53, 3899),
(53, 3900),
(53, 3901),
(53, 3902),
(53, 3903),
(53, 3904),
(53, 3906),
(53, 3907),
(53, 3908),
(53, 3909),
(53, 3910),
(53, 3911),
(53, 3912),
(53, 3914),
(53, 3915),
(53, 3917),
(53, 3918),
(53, 3921),
(53, 3922),
(53, 3925);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `Id` int NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_Id` int DEFAULT NULL,
  `actif` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`Id`, `nom`, `prenom`, `login`, `password`, `role_Id`, `actif`) VALUES
(30, 'Test', 'Admin', 'admin', '$2a$10$F94qOmbEHYrX0mr2ZelzKup7NwukjU0S1yj5JcSg3Ros1knvvEsxe', 1, b'1'),
(42, 'Mohamed', 'Saber', 'med', '$2a$10$HfikMTwao1GX.512s4HIIuGl7NVEZNZ9rq1hUteZJAnvSDby.lSkq', 2, b'1'),
(43, 'Soukaina', 'El Hadifi', 'soukaina', '$2a$10$ajDTVVlGNxO89ctqQ/r8TuMoyqDoSP6hwyLC.YJKaGNxOJaXGGG..', 2, b'1'),
(44, 'Henri', 'Lee', 'lee', '$2a$10$1WIhYCEWBQ9xZEQDaK9mEOlX0yeDjksOs.EV.g7qD5s5zTGD/eAD2', 2, b'1'),
(45, 'test', 'A', 'test', '$2a$10$u5RhDc7abTZRFl6mt.Nxdudgr0wYSZkz5bKmrz2Wx1QdBdoS1AN9a', 2, b'0');
--
-- Index pour les tables déchargées
--

--
-- Index pour la table `administrateur`
--
ALTER TABLE `administrateur`
  ADD PRIMARY KEY (`utilisateur_id`);

--
-- Index pour la table `annotateur`
--
ALTER TABLE `annotateur`
  ADD PRIMARY KEY (`utilisateur_id`);

--
-- Index pour la table `annotation`
--
ALTER TABLE `annotation`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `classeChoisieId` (`classe_choisie_Id`),
  ADD KEY `coupleTexteId` (`couple_texte_Id`),
  ADD KEY `FKoqf0bnu6n4nxoq8knisclci1q` (`annotateur_Id`);

--
-- Index pour la table `classe_possible`
--
ALTER TABLE `classe_possible`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `datasetId` (`dataset_Id`);

--
-- Index pour la table `couple_texte`
--
ALTER TABLE `couple_texte`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `FKdvmycmj4kc5dnej4ojj6khr8m` (`dataset_id`);

--
-- Index pour la table `dataset`
--
ALTER TABLE `dataset`
  ADD PRIMARY KEY (`Id`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`Id`);

--
-- Index pour la table `tache`
--
ALTER TABLE `tache`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `datasetId` (`dataset_Id`),
  ADD KEY `FKakv14loywg6y8v7nr01gcl2hi` (`annotateur_id`);

--
-- Index pour la table `tache_couple_texte`
--
ALTER TABLE `tache_couple_texte`
  ADD PRIMARY KEY (`tache_id`,`couple_texte_id`),
  ADD KEY `couple_texte_id` (`couple_texte_id`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `login` (`login`),
  ADD KEY `roleId` (`role_Id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `annotation`
--
ALTER TABLE `annotation`
  MODIFY `Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT pour la table `classe_possible`
--
ALTER TABLE `classe_possible`
  MODIFY `Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT pour la table `couple_texte`
--
ALTER TABLE `couple_texte`
  MODIFY `Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3926;

--
-- AUTO_INCREMENT pour la table `dataset`
--
ALTER TABLE `dataset`
  MODIFY `Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `tache`
--
ALTER TABLE `tache`
  MODIFY `Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `administrateur`
--
ALTER TABLE `administrateur`
  ADD CONSTRAINT `FKap8vk068oh8c0qxcjbdsoewd0` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`Id`);

--
-- Contraintes pour la table `annotation`
--
ALTER TABLE `annotation`
  ADD CONSTRAINT `FKoqf0bnu6n4nxoq8knisclci1q` FOREIGN KEY (`annotateur_Id`) REFERENCES `annotateur` (`utilisateur_id`),
  ADD CONSTRAINT `annotation_ibfk_1` FOREIGN KEY (`classe_choisie_Id`) REFERENCES `classe_possible` (`Id`),
  ADD CONSTRAINT `annotation_ibfk_2` FOREIGN KEY (`couple_texte_Id`) REFERENCES `couple_texte` (`Id`);

--
-- Contraintes pour la table `classe_possible`
--
ALTER TABLE `classe_possible`
  ADD CONSTRAINT `classe_possible_ibfk_1` FOREIGN KEY (`dataset_Id`) REFERENCES `dataset` (`Id`);

--
-- Contraintes pour la table `couple_texte`
--
ALTER TABLE `couple_texte`
  ADD CONSTRAINT `FKdvmycmj4kc5dnej4ojj6khr8m` FOREIGN KEY (`dataset_id`) REFERENCES `dataset` (`Id`);

--
-- Contraintes pour la table `tache`
--
ALTER TABLE `tache`
  ADD CONSTRAINT `FKakv14loywg6y8v7nr01gcl2hi` FOREIGN KEY (`annotateur_id`) REFERENCES `annotateur` (`utilisateur_id`),
  ADD CONSTRAINT `tache_ibfk_1` FOREIGN KEY (`dataset_Id`) REFERENCES `dataset` (`Id`);

--
-- Contraintes pour la table `tache_couple_texte`
--
ALTER TABLE `tache_couple_texte`
  ADD CONSTRAINT `tache_couple_texte_ibfk_1` FOREIGN KEY (`tache_id`) REFERENCES `tache` (`Id`) ON DELETE CASCADE,
  ADD CONSTRAINT `tache_couple_texte_ibfk_2` FOREIGN KEY (`couple_texte_id`) REFERENCES `couple_texte` (`Id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `utilisateur_ibfk_1` FOREIGN KEY (`role_Id`) REFERENCES `role` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
