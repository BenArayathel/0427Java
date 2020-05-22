import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'cappipe'
})

export class CappipePipe implements PipeTransform {
	
	transform(param: number) {
		return param.toPrecision(3);
	}
}