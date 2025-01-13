--liquibase formatted sql

--changeset ryabchikov:1
INSERT INTO comments (id, text, time, news_id, author_id)
VALUES
    -- Comments for News 1
    (gen_random_uuid(), 'Comment 1 on News 1', NOW(), 'e081a618-7a02-4e81-852d-4caf3debbc20', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 2 on News 1', NOW(), 'e081a618-7a02-4e81-852d-4caf3debbc20', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 3 on News 1', NOW(), 'e081a618-7a02-4e81-852d-4caf3debbc20', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 4 on News 1', NOW(), 'e081a618-7a02-4e81-852d-4caf3debbc20', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 5 on News 1', NOW(), 'e081a618-7a02-4e81-852d-4caf3debbc20', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 6 on News 1', NOW(), 'e081a618-7a02-4e81-852d-4caf3debbc20', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 7 on News 1', NOW(), 'e081a618-7a02-4e81-852d-4caf3debbc20', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 8 on News 1', NOW(), 'e081a618-7a02-4e81-852d-4caf3debbc20', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 9 on News 1', NOW(), 'e081a618-7a02-4e81-852d-4caf3debbc20', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 10 on News 1', NOW(), 'e081a618-7a02-4e81-852d-4caf3debbc20', gen_random_uuid()),

    -- Comments for News 2
    (gen_random_uuid(), 'Comment 1 on News 2', NOW(), '382ba352-2e36-4de5-b9de-0a5820cb4ed7', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 2 on News 2', NOW(), '382ba352-2e36-4de5-b9de-0a5820cb4ed7', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 3 on News 2', NOW(), '382ba352-2e36-4de5-b9de-0a5820cb4ed7', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 4 on News 2', NOW(), '382ba352-2e36-4de5-b9de-0a5820cb4ed7', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 5 on News 2', NOW(), '382ba352-2e36-4de5-b9de-0a5820cb4ed7', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 6 on News 2', NOW(), '382ba352-2e36-4de5-b9de-0a5820cb4ed7', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 7 on News 2', NOW(), '382ba352-2e36-4de5-b9de-0a5820cb4ed7', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 8 on News 2', NOW(), '382ba352-2e36-4de5-b9de-0a5820cb4ed7', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 9 on News 2', NOW(), '382ba352-2e36-4de5-b9de-0a5820cb4ed7', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 10 on News 2', NOW(), '382ba352-2e36-4de5-b9de-0a5820cb4ed7', gen_random_uuid()),

    -- Comments for News 3
    (gen_random_uuid(), 'Comment 1 on News 3', NOW(), '9a54d9ac-82a4-40cb-9c2f-ff6f53dab26d', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 2 on News 3', NOW(), '9a54d9ac-82a4-40cb-9c2f-ff6f53dab26d', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 3 on News 3', NOW(), '9a54d9ac-82a4-40cb-9c2f-ff6f53dab26d', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 4 on News 3', NOW(), '9a54d9ac-82a4-40cb-9c2f-ff6f53dab26d', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 5 on News 3', NOW(), '9a54d9ac-82a4-40cb-9c2f-ff6f53dab26d', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 6 on News 3', NOW(), '9a54d9ac-82a4-40cb-9c2f-ff6f53dab26d', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 7 on News 3', NOW(), '9a54d9ac-82a4-40cb-9c2f-ff6f53dab26d', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 8 on News 3', NOW(), '9a54d9ac-82a4-40cb-9c2f-ff6f53dab26d', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 9 on News 3', NOW(), '9a54d9ac-82a4-40cb-9c2f-ff6f53dab26d', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 10 on News 3', NOW(), '9a54d9ac-82a4-40cb-9c2f-ff6f53dab26d', gen_random_uuid()),

    -- Comments for News 4
    (gen_random_uuid(), 'Comment 1 on News 4', NOW(), 'e92a06d1-5103-4e0d-a001-a8a6febf22b8', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 2 on News 4', NOW(), 'e92a06d1-5103-4e0d-a001-a8a6febf22b8', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 3 on News 4', NOW(), 'e92a06d1-5103-4e0d-a001-a8a6febf22b8', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 4 on News 4', NOW(), 'e92a06d1-5103-4e0d-a001-a8a6febf22b8', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 5 on News 4', NOW(), 'e92a06d1-5103-4e0d-a001-a8a6febf22b8', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 6 on News 4', NOW(), 'e92a06d1-5103-4e0d-a001-a8a6febf22b8', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 7 on News 4', NOW(), 'e92a06d1-5103-4e0d-a001-a8a6febf22b8', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 8 on News 4', NOW(), 'e92a06d1-5103-4e0d-a001-a8a6febf22b8', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 9 on News 4', NOW(), 'e92a06d1-5103-4e0d-a001-a8a6febf22b8', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 10 on News 4', NOW(), 'e92a06d1-5103-4e0d-a001-a8a6febf22b8', gen_random_uuid()),

    -- Comments for News 5
    (gen_random_uuid(), 'Comment 1 on News 5', NOW(), 'ce68d7ed-e750-48a5-bb3c-3a1942957b40', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 2 on News 5', NOW(), 'ce68d7ed-e750-48a5-bb3c-3a1942957b40', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 3 on News 5', NOW(), 'ce68d7ed-e750-48a5-bb3c-3a1942957b40', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 4 on News 5', NOW(), 'ce68d7ed-e750-48a5-bb3c-3a1942957b40', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 5 on News 5', NOW(), 'ce68d7ed-e750-48a5-bb3c-3a1942957b40', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 6 on News 5', NOW(), 'ce68d7ed-e750-48a5-bb3c-3a1942957b40', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 7 on News 5', NOW(), 'ce68d7ed-e750-48a5-bb3c-3a1942957b40', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 8 on News 5', NOW(), 'ce68d7ed-e750-48a5-bb3c-3a1942957b40', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 9 on News 5', NOW(), 'ce68d7ed-e750-48a5-bb3c-3a1942957b40', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 10 on News 5', NOW(), 'ce68d7ed-e750-48a5-bb3c-3a1942957b40', gen_random_uuid()),

    -- Comments for News 6
    (gen_random_uuid(), 'Comment 1 on News 6', NOW(), '273c5355-0962-424b-b4e8-203782fd3350', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 2 on News 6', NOW(), '273c5355-0962-424b-b4e8-203782fd3350', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 3 on News 6', NOW(), '273c5355-0962-424b-b4e8-203782fd3350', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 4 on News 6', NOW(), '273c5355-0962-424b-b4e8-203782fd3350', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 5 on News 6', NOW(), '273c5355-0962-424b-b4e8-203782fd3350', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 6 on News 6', NOW(), '273c5355-0962-424b-b4e8-203782fd3350', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 7 on News 6', NOW(), '273c5355-0962-424b-b4e8-203782fd3350', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 8 on News 6', NOW(), '273c5355-0962-424b-b4e8-203782fd3350', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 9 on News 6', NOW(), '273c5355-0962-424b-b4e8-203782fd3350', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 10 on News 6', NOW(), '273c5355-0962-424b-b4e8-203782fd3350', gen_random_uuid()),

    -- Comments for News 7
    (gen_random_uuid(), 'Comment 1 on News 7', NOW(), '298f8e3b-ed3a-4221-8694-d589cad3d026', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 2 on News 7', NOW(), '298f8e3b-ed3a-4221-8694-d589cad3d026', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 3 on News 7', NOW(), '298f8e3b-ed3a-4221-8694-d589cad3d026', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 4 on News 7', NOW(), '298f8e3b-ed3a-4221-8694-d589cad3d026', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 5 on News 7', NOW(), '298f8e3b-ed3a-4221-8694-d589cad3d026', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 6 on News 7', NOW(), '298f8e3b-ed3a-4221-8694-d589cad3d026', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 7 on News 7', NOW(), '298f8e3b-ed3a-4221-8694-d589cad3d026', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 8 on News 7', NOW(), '298f8e3b-ed3a-4221-8694-d589cad3d026', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 9 on News 7', NOW(), '298f8e3b-ed3a-4221-8694-d589cad3d026', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 10 on News 7', NOW(), '298f8e3b-ed3a-4221-8694-d589cad3d026', gen_random_uuid()),

    -- Comments for News 8
    (gen_random_uuid(), 'Comment 1 on News 8', NOW(), 'ce073750-749d-413b-873d-ad71c0c44bfe', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 2 on News 8', NOW(), 'ce073750-749d-413b-873d-ad71c0c44bfe', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 3 on News 8', NOW(), 'ce073750-749d-413b-873d-ad71c0c44bfe', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 4 on News 8', NOW(), 'ce073750-749d-413b-873d-ad71c0c44bfe', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 5 on News 8', NOW(), 'ce073750-749d-413b-873d-ad71c0c44bfe', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 6 on News 8', NOW(), 'ce073750-749d-413b-873d-ad71c0c44bfe', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 7 on News 8', NOW(), 'ce073750-749d-413b-873d-ad71c0c44bfe', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 8 on News 8', NOW(), 'ce073750-749d-413b-873d-ad71c0c44bfe', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 9 on News 8', NOW(), 'ce073750-749d-413b-873d-ad71c0c44bfe', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 10 on News 8', NOW(), 'ce073750-749d-413b-873d-ad71c0c44bfe', gen_random_uuid()),

    -- Comments for News 9
    (gen_random_uuid(), 'Comment 1 on News 9', NOW(), '7ad3545c-7986-4091-8674-c0b1cdb29b40', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 2 on News 9', NOW(), '7ad3545c-7986-4091-8674-c0b1cdb29b40', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 3 on News 9', NOW(), '7ad3545c-7986-4091-8674-c0b1cdb29b40', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 4 on News 9', NOW(), '7ad3545c-7986-4091-8674-c0b1cdb29b40', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 5 on News 9', NOW(), '7ad3545c-7986-4091-8674-c0b1cdb29b40', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 6 on News 9', NOW(), '7ad3545c-7986-4091-8674-c0b1cdb29b40', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 7 on News 9', NOW(), '7ad3545c-7986-4091-8674-c0b1cdb29b40', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 8 on News 9', NOW(), '7ad3545c-7986-4091-8674-c0b1cdb29b40', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 9 on News 9', NOW(), '7ad3545c-7986-4091-8674-c0b1cdb29b40', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 10 on News 9', NOW(), '7ad3545c-7986-4091-8674-c0b1cdb29b40', gen_random_uuid()),

    -- Comments for News 10
    (gen_random_uuid(), 'Comment 1 on News 10', NOW(), '3c9fdb7a-2c2e-4b07-807a-9341eb647593', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 2 on News 10', NOW(), '3c9fdb7a-2c2e-4b07-807a-9341eb647593', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 3 on News 10', NOW(), '3c9fdb7a-2c2e-4b07-807a-9341eb647593', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 4 on News 10', NOW(), '3c9fdb7a-2c2e-4b07-807a-9341eb647593', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 5 on News 10', NOW(), '3c9fdb7a-2c2e-4b07-807a-9341eb647593', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 6 on News 10', NOW(), '3c9fdb7a-2c2e-4b07-807a-9341eb647593', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 7 on News 10', NOW(), '3c9fdb7a-2c2e-4b07-807a-9341eb647593', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 8 on News 10', NOW(), '3c9fdb7a-2c2e-4b07-807a-9341eb647593', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 9 on News 10', NOW(), '3c9fdb7a-2c2e-4b07-807a-9341eb647593', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 10 on News 10', NOW(), '3c9fdb7a-2c2e-4b07-807a-9341eb647593', gen_random_uuid()),

    -- Comments for News 11
    (gen_random_uuid(), 'Comment 1 on News 11', NOW(), 'eb31fd67-fdaa-4d19-b999-bac2e65218a1', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 2 on News 11', NOW(), 'eb31fd67-fdaa-4d19-b999-bac2e65218a1', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 3 on News 11', NOW(), 'eb31fd67-fdaa-4d19-b999-bac2e65218a1', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 4 on News 11', NOW(), 'eb31fd67-fdaa-4d19-b999-bac2e65218a1', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 5 on News 11', NOW(), 'eb31fd67-fdaa-4d19-b999-bac2e65218a1', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 6 on News 11', NOW(), 'eb31fd67-fdaa-4d19-b999-bac2e65218a1', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 7 on News 11', NOW(), 'eb31fd67-fdaa-4d19-b999-bac2e65218a1', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 8 on News 11', NOW(), 'eb31fd67-fdaa-4d19-b999-bac2e65218a1', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 9 on News 11', NOW(), 'eb31fd67-fdaa-4d19-b999-bac2e65218a1', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 10 on News 11', NOW(), 'eb31fd67-fdaa-4d19-b999-bac2e65218a1', gen_random_uuid()),

    -- Comments for News 12
    (gen_random_uuid(), 'Comment 1 on News 12', NOW(), 'c054c336-c7d6-41dc-a22a-04a8fbb51b10', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 2 on News 12', NOW(), 'c054c336-c7d6-41dc-a22a-04a8fbb51b10', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 3 on News 12', NOW(), 'c054c336-c7d6-41dc-a22a-04a8fbb51b10', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 4 on News 12', NOW(), 'c054c336-c7d6-41dc-a22a-04a8fbb51b10', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 5 on News 12', NOW(), 'c054c336-c7d6-41dc-a22a-04a8fbb51b10', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 6 on News 12', NOW(), 'c054c336-c7d6-41dc-a22a-04a8fbb51b10', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 7 on News 12', NOW(), 'c054c336-c7d6-41dc-a22a-04a8fbb51b10', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 8 on News 12', NOW(), 'c054c336-c7d6-41dc-a22a-04a8fbb51b10', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 9 on News 12', NOW(), 'c054c336-c7d6-41dc-a22a-04a8fbb51b10', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 10 on News 12', NOW(), 'c054c336-c7d6-41dc-a22a-04a8fbb51b10', gen_random_uuid()),

    -- Comments for News 13
    (gen_random_uuid(), 'Comment 1 on News 13', NOW(), 'cdb4b811-3100-4f6f-963a-0a94f8a23135', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 2 on News 13', NOW(), 'cdb4b811-3100-4f6f-963a-0a94f8a23135', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 3 on News 13', NOW(), 'cdb4b811-3100-4f6f-963a-0a94f8a23135', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 4 on News 13', NOW(), 'cdb4b811-3100-4f6f-963a-0a94f8a23135', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 5 on News 13', NOW(), 'cdb4b811-3100-4f6f-963a-0a94f8a23135', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 6 on News 13', NOW(), 'cdb4b811-3100-4f6f-963a-0a94f8a23135', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 7 on News 13', NOW(), 'cdb4b811-3100-4f6f-963a-0a94f8a23135', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 8 on News 13', NOW(), 'cdb4b811-3100-4f6f-963a-0a94f8a23135', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 9 on News 13', NOW(), 'cdb4b811-3100-4f6f-963a-0a94f8a23135', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 10 on News 13', NOW(), 'cdb4b811-3100-4f6f-963a-0a94f8a23135', gen_random_uuid()),

    -- Comments for News 14
    (gen_random_uuid(), 'Comment 1 on News 14', NOW(), '57eb3852-4776-4049-b2c4-6a76dbbc73fd', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 2 on News 14', NOW(), '57eb3852-4776-4049-b2c4-6a76dbbc73fd', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 3 on News 14', NOW(), '57eb3852-4776-4049-b2c4-6a76dbbc73fd', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 4 on News 14', NOW(), '57eb3852-4776-4049-b2c4-6a76dbbc73fd', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 5 on News 14', NOW(), '57eb3852-4776-4049-b2c4-6a76dbbc73fd', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 6 on News 14', NOW(), '57eb3852-4776-4049-b2c4-6a76dbbc73fd', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 7 on News 14', NOW(), '57eb3852-4776-4049-b2c4-6a76dbbc73fd', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 8 on News 14', NOW(), '57eb3852-4776-4049-b2c4-6a76dbbc73fd', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 9 on News 14', NOW(), '57eb3852-4776-4049-b2c4-6a76dbbc73fd', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 10 on News 14', NOW(), '57eb3852-4776-4049-b2c4-6a76dbbc73fd', gen_random_uuid()),

    -- Comments for News 15
    (gen_random_uuid(), 'Comment 1 on News 15', NOW(), 'fd0751e0-7826-412c-addc-abedef7dc18b', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 2 on News 15', NOW(), 'fd0751e0-7826-412c-addc-abedef7dc18b', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 3 on News 15', NOW(), 'fd0751e0-7826-412c-addc-abedef7dc18b', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 4 on News 15', NOW(), 'fd0751e0-7826-412c-addc-abedef7dc18b', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 5 on News 15', NOW(), 'fd0751e0-7826-412c-addc-abedef7dc18b', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 6 on News 15', NOW(), 'fd0751e0-7826-412c-addc-abedef7dc18b', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 7 on News 15', NOW(), 'fd0751e0-7826-412c-addc-abedef7dc18b', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 8 on News 15', NOW(), 'fd0751e0-7826-412c-addc-abedef7dc18b', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 9 on News 15', NOW(), 'fd0751e0-7826-412c-addc-abedef7dc18b', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 10 on News 15', NOW(), 'fd0751e0-7826-412c-addc-abedef7dc18b', gen_random_uuid()),

    -- Comments for News 16
    (gen_random_uuid(), 'Comment 1 on News 16', NOW(), '0608d3d6-93f3-4f5a-8187-fccb649f9618', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 2 on News 16', NOW(), '0608d3d6-93f3-4f5a-8187-fccb649f9618', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 3 on News 16', NOW(), '0608d3d6-93f3-4f5a-8187-fccb649f9618', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 4 on News 16', NOW(), '0608d3d6-93f3-4f5a-8187-fccb649f9618', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 5 on News 16', NOW(), '0608d3d6-93f3-4f5a-8187-fccb649f9618', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 6 on News 16', NOW(), '0608d3d6-93f3-4f5a-8187-fccb649f9618', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 7 on News 16', NOW(), '0608d3d6-93f3-4f5a-8187-fccb649f9618', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 8 on News 16', NOW(), '0608d3d6-93f3-4f5a-8187-fccb649f9618', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 9 on News 16', NOW(), '0608d3d6-93f3-4f5a-8187-fccb649f9618', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 10 on News 16', NOW(), '0608d3d6-93f3-4f5a-8187-fccb649f9618', gen_random_uuid()),

    -- Comments for News 17
    (gen_random_uuid(), 'Comment 1 on News 17', NOW(), 'c346adb9-f61a-4852-a261-420878696613', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 2 on News 17', NOW(), 'c346adb9-f61a-4852-a261-420878696613', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 3 on News 17', NOW(), 'c346adb9-f61a-4852-a261-420878696613', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 4 on News 17', NOW(), 'c346adb9-f61a-4852-a261-420878696613', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 5 on News 17', NOW(), 'c346adb9-f61a-4852-a261-420878696613', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 6 on News 17', NOW(), 'c346adb9-f61a-4852-a261-420878696613', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 7 on News 17', NOW(), 'c346adb9-f61a-4852-a261-420878696613', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 8 on News 17', NOW(), 'c346adb9-f61a-4852-a261-420878696613', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 9 on News 17', NOW(), 'c346adb9-f61a-4852-a261-420878696613', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 10 on News 17', NOW(), 'c346adb9-f61a-4852-a261-420878696613', gen_random_uuid()),

    -- Comments for News 18
    (gen_random_uuid(), 'Comment 1 on News 18', NOW(), '0fded22b-a932-4940-9416-00834e2d5c7e', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 2 on News 18', NOW(), '0fded22b-a932-4940-9416-00834e2d5c7e', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 3 on News 18', NOW(), '0fded22b-a932-4940-9416-00834e2d5c7e', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 4 on News 18', NOW(), '0fded22b-a932-4940-9416-00834e2d5c7e', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 5 on News 18', NOW(), '0fded22b-a932-4940-9416-00834e2d5c7e', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 6 on News 18', NOW(), '0fded22b-a932-4940-9416-00834e2d5c7e', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 7 on News 18', NOW(), '0fded22b-a932-4940-9416-00834e2d5c7e', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 8 on News 18', NOW(), '0fded22b-a932-4940-9416-00834e2d5c7e', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 9 on News 18', NOW(), '0fded22b-a932-4940-9416-00834e2d5c7e', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 10 on News 18', NOW(), '0fded22b-a932-4940-9416-00834e2d5c7e', gen_random_uuid()),

    -- Comments for News 19
    (gen_random_uuid(), 'Comment 1 on News 19', NOW(), 'a12e2a15-ec6e-46e6-8ba4-889c5d3ed275', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 2 on News 19', NOW(), 'a12e2a15-ec6e-46e6-8ba4-889c5d3ed275', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 3 on News 19', NOW(), 'a12e2a15-ec6e-46e6-8ba4-889c5d3ed275', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 4 on News 19', NOW(), 'a12e2a15-ec6e-46e6-8ba4-889c5d3ed275', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 5 on News 19', NOW(), 'a12e2a15-ec6e-46e6-8ba4-889c5d3ed275', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 6 on News 19', NOW(), 'a12e2a15-ec6e-46e6-8ba4-889c5d3ed275', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 7 on News 19', NOW(), 'a12e2a15-ec6e-46e6-8ba4-889c5d3ed275', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 8 on News 19', NOW(), 'a12e2a15-ec6e-46e6-8ba4-889c5d3ed275', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 9 on News 19', NOW(), 'a12e2a15-ec6e-46e6-8ba4-889c5d3ed275', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 10 on News 19', NOW(), 'a12e2a15-ec6e-46e6-8ba4-889c5d3ed275', gen_random_uuid()),

    -- Comments for News 20
    (gen_random_uuid(), 'Comment 1 on News 20', NOW(), 'b336af0c-2ade-4a8f-8622-f2a09a97a7b3', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 2 on News 20', NOW(), 'b336af0c-2ade-4a8f-8622-f2a09a97a7b3', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 3 on News 20', NOW(), 'b336af0c-2ade-4a8f-8622-f2a09a97a7b3', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 4 on News 20', NOW(), 'b336af0c-2ade-4a8f-8622-f2a09a97a7b3', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 5 on News 20', NOW(), 'b336af0c-2ade-4a8f-8622-f2a09a97a7b3', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 6 on News 20', NOW(), 'b336af0c-2ade-4a8f-8622-f2a09a97a7b3', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 7 on News 20', NOW(), 'b336af0c-2ade-4a8f-8622-f2a09a97a7b3', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 8 on News 20', NOW(), 'b336af0c-2ade-4a8f-8622-f2a09a97a7b3', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 9 on News 20', NOW(), 'b336af0c-2ade-4a8f-8622-f2a09a97a7b3', gen_random_uuid()),
    (gen_random_uuid(), 'Comment 10 on News 20', NOW(), 'b336af0c-2ade-4a8f-8622-f2a09a97a7b3', gen_random_uuid());




