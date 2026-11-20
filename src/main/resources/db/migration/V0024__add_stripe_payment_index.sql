create unique index if not exists ux_tb_pagamento_referencial_externa
    on tb_pagamento (referencial_externa)
    where referencial_externa is not null;
