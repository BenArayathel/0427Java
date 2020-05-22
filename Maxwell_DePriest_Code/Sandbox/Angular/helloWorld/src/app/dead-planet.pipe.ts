import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'deadPlanet'
})
export class DeadPlanetPipe implements PipeTransform {

  transform(planetName: string, status: string): string {
   
    if(planetName == "Alderan") {
      status = "- destroyed";
      return(planetName.concat(status));
    }
    else{
      return(planetName.concat(status))
    }

    return null;
  }

}
