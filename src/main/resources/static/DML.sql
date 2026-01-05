-- Insert: Darth Vader
INSERT INTO Figure (
    Name,
    CHARACTER_TYPE,
    Series,
    RELEASE_YEAR,
    Condition,
    IMAGE_PATH,
    ESTIMATED_VALUE
) VALUES (
             'Darth Vader',
             'Sith Lord',
             'Star Wars: Original Trilogy',
             1977,
             'Mint',
             'https://www.dystryktzero.pl/images/detailed/452/x_hono914299.jpg',
             150.00
         );

-- Insert: Luke Skywalker
INSERT INTO Figure (
    Name,
    CHARACTER_TYPE,
    Series,
    RELEASE_YEAR,
    Condition,
    IMAGE_PATH,
    ESTIMATED_VALUE
)  VALUES (
              'Luke Skywalker',
              'Jedi Knight',
              'Star Wars: Original Trilogy',
              1980,
              'Mint',
              'https://www.dystryktzero.pl/images/detailed/346/x_hot913364.jpg',
              135.00
          );

-- Insert: Leia Organa
INSERT INTO Figure (
    Name,
    CHARACTER_TYPE,
    Series,
    RELEASE_YEAR,
    Condition,
    IMAGE_PATH,
    ESTIMATED_VALUE
)  VALUES (
              'Leia Organa',
              'Rebel Leader',
              'Star Wars: Original Trilogy',
              1980,
              'Mint',
              'https://www.dystryktzero.pl/images/thumbnails/500/350/detailed/374/x_hasg1552.jpg',
              120.00
          );

-- Darth Vader submission
INSERT INTO PUBLIC_CATALOG_SUBMISSION (
    STATUS,
    SUBMISSION_DATE,
    FIGURE_ID,
    SUBMITTER_ID
) VALUES (
             'APPROVED',
             CURRENT_DATE,
             1,
             1  -- Replace with actual user ID
         );

-- Darth Vader submission
INSERT INTO PUBLIC_CATALOG_SUBMISSION (
    STATUS,
    SUBMISSION_DATE,
    FIGURE_ID,
    SUBMITTER_ID
) VALUES (
             'APPROVED',
             CURRENT_DATE,
             1,
             1  -- Replace with actual user ID
         );

-- Luke Skywalker submission
INSERT INTO PUBLIC_CATALOG_SUBMISSION (
    STATUS,
    SUBMISSION_DATE,
    FIGURE_ID,
    SUBMITTER_ID
) VALUES (
             'APPROVED',
             CURRENT_DATE,
             2,
             1  -- Replace with actual user ID
         );

-- Leia Organa submission
INSERT INTO PUBLIC_CATALOG_SUBMISSION (
    STATUS,
    SUBMISSION_DATE,
    FIGURE_ID,
    SUBMITTER_ID
) VALUES (
             'APPROVED',
             CURRENT_DATE,
             3,
             1  -- Replace with actual user ID
         );

-- Assuming each figure has 5 history points, spaced one month apart

INSERT INTO price_history (estimated_value, recorded_at, figure_id) VALUES
-- Figure 1
(100.0, '2024-01-01 00:00:00', 1),
(105.0, '2024-02-01 00:00:00', 1),
(110.0, '2024-03-01 00:00:00', 1),
(115.0, '2024-04-01 00:00:00', 1),
(120.0, '2024-05-01 00:00:00', 1),

-- Figure 2
(80.0, '2024-01-01 00:00:00', 2),
(78.0, '2024-02-01 00:00:00', 2),
(75.0, '2024-03-01 00:00:00', 2),
(77.0, '2024-04-01 00:00:00', 2),
(79.0, '2024-05-01 00:00:00', 2),

-- Figure 3
(200.0, '2024-01-01 00:00:00', 3),
(210.0, '2024-02-01 00:00:00', 3),
(220.0, '2024-03-01 00:00:00', 3),
(230.0, '2024-04-01 00:00:00', 3),
(240.0, '2024-05-01 00:00:00', 3),

-- Figure 4
(50.0, '2024-01-01 00:00:00', 4),
(52.0, '2024-02-01 00:00:00', 4),
(51.0, '2024-03-01 00:00:00', 4),
(53.0, '2024-04-01 00:00:00', 4),
(55.0, '2024-05-01 00:00:00', 4),

-- Figure 5
(150.0, '2024-01-01 00:00:00', 5),
(145.0, '2024-02-01 00:00:00', 5),
(140.0, '2024-03-01 00:00:00', 5),
(138.0, '2024-04-01 00:00:00', 5),
(135.0, '2024-05-01 00:00:00', 5);

-- Darth Vader (Figure 1)
INSERT INTO price_history (estimated_value, recorded_at, figure_id) VALUES
                                                                        (122.0, '2024-06-01 00:00:00', 1),
                                                                        (118.0, '2024-07-01 00:00:00', 1),
                                                                        (123.0, '2024-08-01 00:00:00', 1),
                                                                        (127.0, '2024-09-01 00:00:00', 1),
                                                                        (125.0, '2024-10-01 00:00:00', 1),
                                                                        (130.0, '2024-11-01 00:00:00', 1),
                                                                        (128.0, '2024-12-01 00:00:00', 1);

-- Luke Skywalker (Figure 2)
INSERT INTO price_history (estimated_value, recorded_at, figure_id) VALUES
                                                                        (80.0, '2024-06-01 00:00:00', 2),
                                                                        (83.0, '2024-07-01 00:00:00', 2),
                                                                        (78.0, '2024-08-01 00:00:00', 2),
                                                                        (76.0, '2024-09-01 00:00:00', 2),
                                                                        (79.0, '2024-10-01 00:00:00', 2),
                                                                        (82.0, '2024-11-01 00:00:00', 2),
                                                                        (81.0, '2024-12-01 00:00:00', 2);

-- Leia Organa (Figure 3)
INSERT INTO price_history (estimated_value, recorded_at, figure_id) VALUES
                                                                        (242.0, '2024-06-01 00:00:00', 3),
                                                                        (239.0, '2024-07-01 00:00:00', 3),
                                                                        (250.0, '2024-08-01 00:00:00', 3),
                                                                        (245.0, '2024-09-01 00:00:00', 3),
                                                                        (255.0, '2024-10-01 00:00:00', 3),
                                                                        (260.0, '2024-11-01 00:00:00', 3),
                                                                        (258.0, '2024-12-01 00:00:00', 3);

-- Figure 4 (unspecified name)
INSERT INTO price_history (estimated_value, recorded_at, figure_id) VALUES
                                                                        (56.0, '2024-06-01 00:00:00', 4),
                                                                        (54.0, '2024-07-01 00:00:00', 4),
                                                                        (55.0, '2024-08-01 00:00:00', 4),
                                                                        (57.0, '2024-09-01 00:00:00', 4),
                                                                        (56.0, '2024-10-01 00:00:00', 4),
                                                                        (58.0, '2024-11-01 00:00:00', 4),
                                                                        (57.0, '2024-12-01 00:00:00', 4);

-- Figure 5 (unspecified name)
INSERT INTO price_history (estimated_value, recorded_at, figure_id) VALUES
                                                                        (134.0, '2024-06-01 00:00:00', 5),
                                                                        (138.0, '2024-07-01 00:00:00', 5),
                                                                        (137.0, '2024-08-01 00:00:00', 5),
                                                                        (140.0, '2024-09-01 00:00:00', 5),
                                                                        (136.0, '2024-10-01 00:00:00', 5),
                                                                        (138.0, '2024-11-01 00:00:00', 5),
                                                                        (141.0, '2024-12-01 00:00:00', 5);
