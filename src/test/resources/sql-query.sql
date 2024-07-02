select ord.id, pa.status as payment, cr.status as credit
from app.order_entity ord
         left join app.payment_entity pa on ord.payment_id = pa.transaction_id
         left join app.credit_request_entity cr on ord.payment_id = cr.bank_id;