import Beneficiary from '@/model/entity/Beneficiary'
import User from '@/model/entity/User'
import Uptake from '@/model/entity/Uptake'

export default class InvoiceSap {
  // eslint-disable-next-line
    constructor(
        public id?: number,
        public invoiceId?: number,
        public number?: number,
        public dateOfIssue?: string,
        public totalToPay: number = 0,
        public payed: boolean = false,
        public beneficiary: Beneficiary = new Beneficiary(),
        public debtcollector: User = new User(),
        public detail: Uptake[] = []
  ) { }
}
