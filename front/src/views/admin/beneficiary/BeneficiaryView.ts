import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
// @ts-ignore
import sweetalert from 'sweetalert'
import Beneficiary from '@/model/entity/Beneficiary'
import BeneficiaryService from '@/model/service/BeneficiaryService'
import VillageService from '@/model/service/VillageService'

import FindVillage from '@/components/village/FindVillage.vue'
import Village from '@/model/entity/Village'

@Component({
  name: 'v-beneficiary',
  components: { FindVillage }
})
export default class BeneficiaryComponent extends Page {
  public beneficiaries: Beneficiary[] = [];
  public search: string = '';
  public dialog: boolean = false;
  public editedIndex: number = -1;
  public editedItem: Beneficiary = new Beneficiary();
  public headers: any[] = [
    { text: 'Apellidos', value: 'lastName', align: 'left' },
    { text: 'Nombres', value: 'firstName', align: 'left' },
    { text: 'Cédula', value: 'dni', sortable: false },
    { text: 'Comunidad', value: 'village.name' },
    { text: 'Teléfono', value: 'telephone', sortable: false },
    { text: 'Dirección', value: 'address', sortable: false },
    { text: 'Referencia', value: 'placeReference', sortable: false },
    { text: 'Acciones', sortable: false }
  ];

  public created (): void {
    this.findBeneficiaries()
  }

  public setVillage (village: Village): void {
    this.editedItem.village = village
  }

  public findBeneficiaries (): void {
    const beneficiaryService = new BeneficiaryService()
    this.beneficiaries = []
    beneficiaryService.getAll().then((res: any) => {
      this.beneficiaries = res.data
    }).catch(() => {
      this.error('Error al buscar beneficiarios')
    })
  }

  public save (): void {
    const beneficiaryService = new BeneficiaryService(this.getUser().id)
    beneficiaryService.post(this.editedItem).then((res: any) => {
      if (res.data.saved === true) {
        this.beneficiaries.push(res.data.beneficiary)
        this.success('Beneficiario guardado')
        this.close()
      }
    }).catch((err) => {
      console.log(err.response)
      this.error('Beneficiario sin guardar', err.response.data.error)
    })
  }

  public update (): void {
    const beneficiaryService = new BeneficiaryService(this.getUser().id)
    beneficiaryService.put(this.editedItem).then((res) => {
      if (res.data.updated === true) {
        this.beneficiaries[this.editedIndex] = this.editedItem
        this.success('Beneficiario actualizado')
        this.close()
      }
    }).catch((err) => {
      this.error('Beneficiario sin actualizar', err.response.data.error)
    })
  }

  public submit (): void {
    if (this.editedIndex > -1) {
      this.update()
    } else {
      this.save()
    }
  }

  public remove (beneficiary: Beneficiary): void {
    const beneficiaryService = new BeneficiaryService(this.getUser().id)
    const conf: any = {
      buttons: true,
      title: '¿Está seguro?',
      text: `Una vez que se elimine, ¡no podrá recuperar el registro de ` +
      `${beneficiary.lastName} ${beneficiary.firstName}!`,
      icon: 'warning',
      dangerMode: true
    }

    sweetalert(conf)
      .then((willDelete:any) => {
        if (willDelete) {
          beneficiaryService.delete(beneficiary).then((res: any) => {
            if (res.data.deleted === true) {
              const index = this.beneficiaries.indexOf(beneficiary)
              this.beneficiaries.splice(index, 1)
              this.success('Beneficiario eliminado')
            }
          }).catch((err) => {
            this.error('Beneficiario sin eliminar', err.response.data.error)
          })
        }
      })
  }

  public editItem (beneficiary: Beneficiary): void {
    this.editedIndex = this.beneficiaries.indexOf(beneficiary)
    this.editedItem = beneficiary
    this.dialog = true
  }

  public close (): void {
    this.dialog = false
    setTimeout(() => {
      this.editedItem = new Beneficiary()
      this.editedIndex = -1
    }, 300)
  }
}
