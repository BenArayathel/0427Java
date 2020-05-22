import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'forceAffiliation'
})
export class ForceAffiliationPipe implements PipeTransform {

  transform(name: string, affiliation: string): string {
    return (name.concat(affiliation));
  }

}
