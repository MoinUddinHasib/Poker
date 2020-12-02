# Poker
----------------------------------------------------------------------------------------------------

Prima di fare il primo run settare: [property name="generateDdl" value="true"] nel file applicationContext.xml

Dopo di che inserire i ruoli manualmente nel DB (nome_schema -> poker):

   | id | Tipo |
   
   | 1  | ADMIN_ROLE |
   
   | 2  | SPECIAL_PLAYER_ROLE |
   
   | 3  | PLAYER_ROLE |
   
Dopo la prima registrazione tramite form andare nel DB e settare lo stato ad 'ATTIVO' aggiungendogli almeno un RUOLO;
