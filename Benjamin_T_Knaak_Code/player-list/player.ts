import { Position } from './position';

export interface Player {
	name: string;
	position: Position;
	ba: number;
	slg: number;
	ops: number;
	hr: number;
	image: string;
}