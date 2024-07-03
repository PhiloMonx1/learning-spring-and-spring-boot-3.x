const person = {
  name: 'EH13',
  address: {
    city: 'Goyang',
    country: 'Korea',
  },
  profiles: ['github', 'linkedin', 'instagram'],
  printProfile: () => {person.profiles.map((profile) => console.log(profile))},
}

export default function LearningJavaScript() {
  return (
      <>
        <div>{person.name}</div>
        <div>{person.address.city}</div>
        <div>{person.address.country}</div>
        <div>{person.profiles[0]}</div>
        <div>{person.profiles[1]}</div>
        <div>{person.profiles[2]}</div>
        <div>{person.printProfile()}</div>
      </>
  );
}